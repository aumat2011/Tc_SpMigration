package com.humana.pharmacy.service.impl;

import com.humana.pharmacy.dto.OrderGPIDTO;
import com.humana.pharmacy.dto.OrderProductDTO;
import com.humana.pharmacy.service.DuplicateGPIService;
import com.humana.pharmacy.common.Helper;
import com.humana.pharmacy.common.entity.QEScriptMsgAttributes;
import com.humana.pharmacy.common.entity.QOrderHeader;
import com.humana.pharmacy.common.entity.QOrderLines;
import com.humana.pharmacy.common.entity.QProducts;
import com.humana.pharmacy.common.entity.QRuleQueueException;
import com.humana.pharmacy.common.service.impl.BaseServiceImpl;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.sql.SQLExpressions;

import javax.sql.DataSource;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Implementation of DuplicateGPIService using QueryDSL.
 */
public class DuplicateGPIServiceImpl extends BaseServiceImpl implements DuplicateGPIService {
    /**
     * The status used to filter order lines.
     */
    private static final Byte ORDER_LINE_STATUS_NUM = 1;
    /**
     * The order open status.
     */
    private static final Byte ORDER_OPEN_STATUS_NUM = 1;
    /**
     * The order incomplete status.
     */
    private static final Byte ORDER_INCOMPLETE_STATUS_NUM = 98;
    /**
     * The DCCO Same GPI DME Code cat id.
     */
    private static final String DCCO_GPI = "DCCO_GPI";
    /**
     * The order base rule tag.
     */
    private static final String IS_BASE_RULE = "Y";
    /**
     * The not order base rule tag.
     */
    private static final String NOT_BASE_RULE = "N";
    /**
     * The char in product desc that need to be removed.
     */
    private static final String PRODUCT_DESC_REP_STR = "&#x0d;";
    /**
     * The error message of duplicate in same order.
     */
    private static final String DUPLICATE_IN_SAME_ORDER = "Duplicate GPI %s within the same Order. ";
    /**
     * The error message of duplicate with order that fax in queue.
     */
    private static final String DUPLICATE_WITH_FAX_IN_QUEUE =
            "Duplicate GPI %s in this Order, %s in Order %s with fax in %s Queue. ";

    /**
     * The error message of duplicate with open order.
     */
    private static final String DUPLICATE_WITH_OPEN_ORDER_IN_QUEUE =
            "Duplicate GPI %s in this Order, %s in Open Order %s in %s Queue. ";

    /**
     * The error message of duplication order with incomplete order.
     */
    private static final String DUPLICATE_ACROSS_INCOMPLETE_ORDER =
            "Duplicate GPI %s in this Order, %s in Incomplete/Pending Order %s. ";

    /**
     * The max length of string. (excluded)
     */
    private static final Integer STRING_MAX_LENGTH = 8000;

    /**
     * The order incomplete status.
     */
    private static final List<String> WORKFLOW_QUEUE_NAMES =
            Arrays.asList("REFILL OUTREACH", "DR. CALL TECH OUTBOUND", "DR. CALL TECH RESPONSE");

    /**
     * The separator used in result message when multiple duplicates found.
     */
    private static final String RESULT_SEPARATOR = "-!-";

    /**
     * Constructor of DuplidateGPIServiceImpl.
     *
     * @param epostrxDataSource  DataSource for epostrx database
     * @param workflowDataSource DataSource for epostrx_workflow database
     * @throws RuntimeException if epostrxDataSource or workflowDataSource is null
     */
    public DuplicateGPIServiceImpl(DataSource epostrxDataSource, DataSource workflowDataSource) {
        super(epostrxDataSource, workflowDataSource);
    }

    /**
     * Load all open & incomplete/pending orders along with dispensed product details for given
     * patient.
     *
     * @param patientNum      the patient num
     * @param orderInvoiceNum The order invoice number to check duplicate. Required to be non-empty.
     * @return List of orders along with product details
     */
    private List<OrderProductDTO> getOpenIncompleteOrders(
            BigInteger patientNum, String orderInvoiceNum) {
        QOrderHeader qOrderHeader = QOrderHeader.orderHeader;
        QOrderLines qOrderLine = QOrderLines.orderLines;
        QProducts qProduct = QProducts.products;
        QEScriptMsgAttributes qEScriptMsgAttribute = QEScriptMsgAttributes.eScriptMsgAttributes;
        QRuleQueueException rqe = QRuleQueueException.ruleQueueException;

        List<String> codeValues = getCodeValues(DCCO_GPI);

        // Rules to get order based queue
        List<String> orderBasedRules = getRules("Y");

        // Subquery to get order based queue
        NumberExpression<Integer> orderQueueNum =
                Expressions.cases()
                        .when(qOrderHeader.orderInvoiceNumber.eq(orderInvoiceNum))
                        .then(0)
                        .otherwise(
                                SQLExpressions.select(rqe.workflowQueueNum.max())
                                        .from(rqe)
                                        .where(
                                                rqe.orderNum.eq(qOrderHeader.orderNum).and(rqe.workflowStepNum.in(orderBasedRules))))
                        .as("orderQueueNum");

        // Rules to get non-order based queue
        List<String> nonOrderBasedRules = getRules("N");

        NumberExpression<Integer> nonOrderQueueNum =
                Expressions.cases()
                        .when(qOrderHeader.orderInvoiceNumber.eq(orderInvoiceNum))
                        .then(0)
                        .otherwise(
                                SQLExpressions.select(rqe.workflowQueueNum.max())
                                        .from(rqe)
                                        .where(
                                                rqe.orderNum
                                                        .eq(qOrderHeader.orderNum)
                                                        .and(rqe.workflowStepNum.in(nonOrderBasedRules))
                                                        .and(rqe.eScriptMsgAttributeSeq.eq(qOrderLine.eScriptMsgAttributeSeq))))
                        .as("nonOrderQueueNum");

        return epostrxQueryFactory
                .select(
                        Projections.bean(
                                OrderProductDTO.class,
                                qOrderHeader.orderNum,
                                qOrderHeader.orderStatusNum,
                                qOrderHeader.orderInvoiceNumber,
                                qProduct.prodNameDesc,
                                qProduct.prodGenericRefNum,
                                orderQueueNum,
                                nonOrderQueueNum))
                .from(qOrderHeader)
                .join(qOrderLine)
                .on(qOrderLine.orderNum.eq(qOrderHeader.orderNum))
                .join(qEScriptMsgAttribute)
                .on(qEScriptMsgAttribute.eScriptMsgAttributeSeq.eq(qOrderLine.eScriptMsgAttributeSeq))
                .join(qProduct)
                .on(qProduct.prodId.eq(qEScriptMsgAttribute.dispensedProductId))
                .where(
                        qOrderHeader
                                .orderStatusNum
                                .in(ORDER_OPEN_STATUS_NUM, ORDER_INCOMPLETE_STATUS_NUM)
                                .and(qOrderLine.orderLineStatusNum.eq(ORDER_LINE_STATUS_NUM))
                                .and(qEScriptMsgAttribute.patientNum.eq(patientNum))
                                .and(qOrderHeader.replaceParentOrderNum.isNull())
                                .and(qProduct.prodGenericRefNum.isNotNull())
                                .and(qProduct.prodGenericRefNum.notIn(codeValues)))
                .fetch();
    }

    /**
     * Check duplicate GPI for given patient and order invoice number.
     *
     * @param patientNum      The patient number. Required to be non-null.
     * @param orderInvoiceNum The order invoice number to check duplicate. Required to be non-empty.
     * @return Duplicate message. Empty string means no duplicate found
     * @throws RuntimeException if patientNum is null or orderInvoiceNum is null/empty
     */
    public String checkDuplicateGPI(BigInteger patientNum, String orderInvoiceNum) {
        Helper.checkNull(patientNum, "patientNum");
        if (orderInvoiceNum == null || orderInvoiceNum.trim().length() == 0) {
            throw new RuntimeException("'orderInvoiceNum' can't be null/empty");
        }
        // Load open and incomplete/pending orders for given patient
        List<OrderProductDTO> dtos = getOpenIncompleteOrders(patientNum, orderInvoiceNum);

        // Group by (order_num, prod_generic_ref_num)
        Map<String, List<OrderProductDTO>> groupByOrder =
                dtos.stream()
                        .collect(
                                Collectors.groupingBy(dto -> dto.getProdGenericRefNum() + "@" + dto.getOrderNum()));

        // Group by (order_invoice_number, prod_generic_ref_num)
        Map<String, List<OrderProductDTO>> groupByInvoice =
                dtos.stream()
                        .collect(
                                Collectors.groupingBy(
                                        dto -> dto.getProdGenericRefNum() + "@" + dto.getOrderInvoiceNumber()));

        List<OrderGPIDTO> gpiDetails = new ArrayList<>();
        // Split to current orders list & other orders list based on given orderInvoiceNum
        List<OrderGPIDTO> gpiCurrentOrders = new ArrayList<>();
        List<OrderGPIDTO> gpiOtherOrders = new ArrayList<>();

        for (List<OrderProductDTO> products : groupByOrder.values()) {
            OrderGPIDTO gpi = new OrderGPIDTO();
            gpi.setOrderNum(products.get(0).getOrderNum());
            gpi.setOrderStatusNum(products.get(0).getOrderStatusNum());
            gpi.setOrderInvoiceNumber(products.get(0).getOrderInvoiceNumber());
            gpi.setProdGenericRefNum(products.get(0).getProdGenericRefNum());

            // Concatenate product names, refer to line 93-98 of P_checkDuplicateGPI.sql
            String prodNameDesc =
                    products.stream()
                            .map(dto -> "'" + dto.getProdNameDesc().replace(PRODUCT_DESC_REP_STR, "") + "'")
                            .collect(Collectors.joining(","));
            gpi.setProdNameDesc(
                    prodNameDesc.substring(0, Math.min(prodNameDesc.length(), STRING_MAX_LENGTH)));

            // Count gpi, refer to line 100 of P_checkDuplicateGPI.sql
            gpi.setGpiCount(
                    groupByInvoice
                            .get(gpi.getProdGenericRefNum() + "@" + gpi.getOrderInvoiceNumber())
                            .size());

            if (gpi.getOrderInvoiceNumber().equals(orderInvoiceNum)) {
                gpiCurrentOrders.add(gpi);
            } else {
                // Use last queue number
                gpi.setOrderQueueNum(products.get(products.size() - 1).getOrderQueueNum());
                gpi.setNonOrderQueueNum(products.get(products.size() - 1).getNonOrderQueueNum());
                gpiOtherOrders.add(gpi);
            }
        }

        // Get queue names
        Set<Integer> queueNums = gpiOtherOrders.stream().map(
                        dto -> dto.getNonOrderQueueNum() != null ? dto.getNonOrderQueueNum() : dto.getOrderQueueNum())
                .filter(num -> num != null).collect(Collectors.toSet());
        Map<Integer, String> queueNames = queueNums.isEmpty() ? null : getWorkflowQueueNames(new ArrayList<>(queueNums));

        // The result message
        StringBuilder result = new StringBuilder();

        // Check duplicate GPI within order, refer to line 157-160 of P_checkDuplicateGPI.sql
        for (OrderGPIDTO gpi : gpiCurrentOrders) {
            if (gpi.getGpiCount() > 1) {
                result
                        .append(String.format(DUPLICATE_IN_SAME_ORDER, gpi.getProdNameDesc()))
                        .append(RESULT_SEPARATOR);
            }
        }

        // Check duplicate GPI across other open orders which are in order based queues and non order based queues
        // refer to line 166-176 of P_checkDuplicateGPI.sql
        for (OrderGPIDTO otherOrder : gpiOtherOrders) {
            Integer queueNum = otherOrder.getNonOrderQueueNum() != null ? otherOrder.getNonOrderQueueNum() : otherOrder.getOrderQueueNum();
            String queueName = queueNum == null || queueNames == null ? null : queueNames.get(queueNum);
            if (queueName != null) {
                List<OrderGPIDTO> currentOrders = gpiCurrentOrders.stream().filter(dto -> dto.getProdGenericRefNum().equals(otherOrder.getProdGenericRefNum()))
                        .collect(Collectors.toList());

                for (OrderGPIDTO currentOrder : currentOrders) {
                    if (WORKFLOW_QUEUE_NAMES.contains(queueName)) {
                        result.append(
                                String.format(
                                        DUPLICATE_WITH_FAX_IN_QUEUE,
                                        currentOrder.getProdNameDesc(),
                                        otherOrder.getProdNameDesc(),
                                        otherOrder.getOrderInvoiceNumber(),
                                        queueName));
                    } else {
                        result.append(
                                String.format(
                                        DUPLICATE_WITH_OPEN_ORDER_IN_QUEUE,
                                        currentOrder.getProdNameDesc(),
                                        otherOrder.getProdNameDesc(),
                                        otherOrder.getOrderInvoiceNumber(),
                                        queueName));
                    }
                    result.append(RESULT_SEPARATOR);
                }
            }
        }
        // Check duplicate GPI across incomplete/pending orders, refer to line 182-188 of
        // P_checkDuplicateGPI.sql
        for (OrderGPIDTO gpi : gpiCurrentOrders) {
            List<OrderGPIDTO> otherGpis =
                    gpiOtherOrders.stream()
                            .filter(
                                    dto ->
                                            dto.getOrderStatusNum().equals(ORDER_INCOMPLETE_STATUS_NUM)
                                                    && dto.getProdGenericRefNum().equals(gpi.getProdGenericRefNum()))
                            .collect(Collectors.toList());
            for (OrderGPIDTO otherGpi : otherGpis) {
                result
                        .append(
                                String.format(
                                        DUPLICATE_ACROSS_INCOMPLETE_ORDER,
                                        gpi.getProdNameDesc(),
                                        otherGpi.getProdNameDesc(),
                                        otherGpi.getOrderInvoiceNumber()))
                        .append(RESULT_SEPARATOR);
            }
        }
        return result.substring(0, Math.min(result.length(), STRING_MAX_LENGTH));
    }
}
