package com.humana.pharmacy.service.impl;

import com.humana.pharmacy.dto.ExceedMaxPVDaysResult;
import com.humana.pharmacy.service.ExceedMaxPVDaysService;
import com.humana.pharmacy.common.Helper;
import com.humana.pharmacy.common.cache.model.CRxDefaults;
import com.humana.pharmacy.common.entity.QEScriptMsgAttributes;
import com.humana.pharmacy.common.entity.QOrderHeader;
import com.humana.pharmacy.common.entity.QOrderLines;
import com.humana.pharmacy.common.entity.QProducts;
import com.humana.pharmacy.common.entity.QRxFillAux;
import com.humana.pharmacy.common.service.impl.BaseServiceImpl;
import com.querydsl.core.JoinFlag;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.sql.SQLExpressions;

import javax.sql.DataSource;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of ExceedMaxPVDaysService using QueryDSL. It extends BaseServiceImpl.
 */
public class ExceedMaxPVDaysServiceImpl extends BaseServiceImpl implements ExceedMaxPVDaysService {
    /**
     * The string 'Y'.
     */
    private static final String FLAG_Y = "Y";

    /**
     * The string 'N'.
     */
    private static final String FLAG_N = "N";

    /**
     * The status '2'.
     */
    private static final byte FILL_STAT_2 = 2;

    /**
     * The status '9'.
     */
    private static final byte FILL_STAT_9 = 9;

    /**
     * The join flag ' WITH (NOLOCK) '.
     */
    private static final String JOIN_FLAG = " WITH (NOLOCK) ";

    /**
     * Constructor of ExceedMaxPVDaysServiceImpl.
     *
     * @param epostrxDataSource  DataSource for epostrx database. Required
     * @param workflowDataSource DataSource for epostrx_workflow database. Required
     * @throws RuntimeException if epostrxDataSource or workflowDataSource is null
     */
    public ExceedMaxPVDaysServiceImpl(DataSource epostrxDataSource, DataSource workflowDataSource) {
        super(epostrxDataSource, workflowDataSource);
    }

    /**
     * Check where order exceeds max PV days.
     *
     * @param orderNum The order num. Required
     * @return Exceed max PV days result. Maybe null if no exceed found.
     * @throws RuntimeException if orderNum is null
     */
    @Override
    public ExceedMaxPVDaysResult checkExceedMaxPVDays(BigInteger orderNum) {
        Helper.checkNull(orderNum, "orderNum");

        QOrderLines qOrderLine = QOrderLines.orderLines;
        QOrderHeader qOrderHeader = QOrderHeader.orderHeader;
        QRxFillAux qRxFillAux = QRxFillAux.rxFillAux;
        QEScriptMsgAttributes qEscript = QEScriptMsgAttributes.eScriptMsgAttributes;
        QProducts qProduct = QProducts.products;

        // Get RxDefaults
        CRxDefaults rxDefaults = getRxDefaults();
        Short maxPVDays = rxDefaults.getMaxPvDays();
        if (maxPVDays == null) {
            maxPVDays = 0;
        }

        BooleanExpression conditions = qOrderLine.orderNum.eq(orderNum)
                .and(qOrderLine.orderLineStatusNum.eq((byte) 1))
                .and(qRxFillAux.fillPrecheckDatetime.isNull())
                .and(qEscript.ediTransactionCode.eq("NEWRX"));

        // Refer to F_itemPrecheckRequired, line 86 ~ 88
        // Remove DME items if DME check not needed
        if (!FLAG_Y.equalsIgnoreCase(rxDefaults.getPwoPrecheck())) {
            conditions = conditions.and(qEscript.pwoItem.coalesce(FLAG_N).ne(FLAG_Y));
        }

        // Refer to F_itemPrecheckRequired, line 86 ~ 88
        // Remove OTC items if OTC check not needed
        if (!FLAG_Y.equalsIgnoreCase(rxDefaults.getOtcAsRxPrecheck())) {
            conditions = conditions.and(qProduct.prodRxRequired.coalesce(FLAG_Y).ne(FLAG_N));
        }

        QEScriptMsgAttributes qEscriptSub = new QEScriptMsgAttributes("eSub");
        QRxFillAux qRxFillAuxSub = new QRxFillAux("rxSub");

        // Refer to F_IsRxFirstTimeFill, check if the item has not been dispense filled
        BooleanExpression subConditions = qRxFillAuxSub.fillStatusNum.in(FILL_STAT_2, FILL_STAT_9);

        // Refer to F_itemPrecheckRequired, line 63 ~ 72
        // check if the item has already been screened at some point in RX life
        if (!FLAG_Y.equalsIgnoreCase(rxDefaults.getPrecheckAll())) {
            subConditions = subConditions.or(qRxFillAuxSub.fillPrecheckDatetime.isNotNull());
        }

        conditions = conditions.and(SQLExpressions
                .select(Expressions.stringTemplate("1"))
                .from(qEscriptSub).addJoinFlag(JOIN_FLAG, JoinFlag.Position.BEFORE_CONDITION)
                .join(qRxFillAuxSub).addJoinFlag(JOIN_FLAG, JoinFlag.Position.BEFORE_CONDITION).on(qRxFillAuxSub.eScriptMsgAttributeSeq.eq(qEscriptSub.eScriptMsgAttributeSeq))
                .where(qEscriptSub.rxNumber.eq(qEscript.rxNumber).and(subConditions))
                .notExists());

        // Refer to F_IsRxExceedMaxPVDays, check if exceed max PV days
        conditions = conditions.and(SQLExpressions.addDays(qEscript.writtenDate, maxPVDays)
                .before(DateTimeExpression.currentTimestamp(Timestamp.class)));

        // Get Rx numbers exceed
        List<Tuple> tuples = epostrxQueryFactory
                .select(qEscript.rxNumber, qOrderHeader.tradingPartnerNum)
                .distinct()
                .from(qEscript).addJoinFlag(JOIN_FLAG, JoinFlag.Position.BEFORE_CONDITION)
                .join(qOrderLine).addJoinFlag(JOIN_FLAG, JoinFlag.Position.BEFORE_CONDITION)
                .on(qOrderLine.eScriptMsgAttributeSeq.eq(qEscript.eScriptMsgAttributeSeq))
                .join(qOrderHeader).addJoinFlag(JOIN_FLAG, JoinFlag.Position.BEFORE_CONDITION)
                .on(qOrderLine.orderNum.eq(qOrderHeader.orderNum))
                .join(qRxFillAux).addJoinFlag(JOIN_FLAG, JoinFlag.Position.BEFORE_CONDITION)
                .on(qRxFillAux.eScriptMsgAttributeSeq.eq(qEscript.eScriptMsgAttributeSeq))
                .leftJoin(qProduct).addJoinFlag(JOIN_FLAG, JoinFlag.Position.BEFORE_CONDITION)
                .on(qProduct.prodId.eq(qEscript.dispensedProductId))
                .where(conditions)
                .fetch();

        if (tuples.isEmpty()) {
            return null;
        }

        // Create result
        ExceedMaxPVDaysResult result = new ExceedMaxPVDaysResult();
        result.setRxNumbers(tuples.stream().map(tuple -> tuple.get(qEscript.rxNumber)).collect(Collectors.toList()));
        result.setMaxPVDays(maxPVDays);

        // Get trading partner number
        Long tpNum = tuples.get(0).get(qOrderHeader.tradingPartnerNum);

        // Get override queue number
        result.setOverrideQueue(getWorkflowQueueNumber("AZ CONSOLIDATE", tpNum));
        result.setOverrideQname("AZ CONSOLIDATE QUEUE");

        return result;
    }
}

