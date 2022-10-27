package com.humana.pharmacy.common.service.impl;

import com.humana.pharmacy.common.Helper;
import com.humana.pharmacy.common.cache.InfinispanCache;
import com.humana.pharmacy.common.cache.model.CCodeValue;
import com.humana.pharmacy.common.cache.model.COrderDefaults;
import com.humana.pharmacy.common.cache.model.CRule;
import com.humana.pharmacy.common.cache.model.CRxDefaults;
import com.humana.pharmacy.common.cache.model.CShippingMethod;
import com.humana.pharmacy.common.cache.model.CWorkflowQueue;
import com.humana.pharmacy.common.constants.CacheName;
import com.humana.pharmacy.common.dto.RsMultiLinkDTO;
import com.humana.pharmacy.common.dto.RxEScriptDTO;
import com.humana.pharmacy.common.entity.QCodeValue;
import com.humana.pharmacy.common.entity.QEScriptMsgAttributes;
import com.humana.pharmacy.common.entity.QOKHydrocodoneSanityProducts;
import com.humana.pharmacy.common.entity.QOrderDefaults;
import com.humana.pharmacy.common.entity.QProducts;
import com.humana.pharmacy.common.entity.QRules;
import com.humana.pharmacy.common.entity.QRxDefaults;
import com.humana.pharmacy.common.entity.QRxFillAux;
import com.humana.pharmacy.common.entity.QRxMultiLink;
import com.humana.pharmacy.common.entity.QRxStatusCodes;
import com.humana.pharmacy.common.entity.QShippingMethods;
import com.humana.pharmacy.common.entity.QSystemUsers;
import com.humana.pharmacy.common.entity.QTradingPartnerGeneral;
import com.humana.pharmacy.common.entity.QTradingPartnerTypes;
import com.humana.pharmacy.common.entity.QWorkflowQueueList;
import com.humana.pharmacy.common.entity.QWorkflowQueueNames;
import com.querydsl.core.JoinFlag;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.sql.Configuration;
import com.querydsl.sql.SQLQuery;
import com.querydsl.sql.SQLQueryFactory;
import com.querydsl.sql.SQLServer2012Templates;

import javax.sql.DataSource;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.humana.pharmacy.common.constants.CacheName.CODE_VALUES;
import static com.humana.pharmacy.common.constants.CacheName.RULES;
import static com.humana.pharmacy.common.constants.CacheName.WORKFLOW_QUEUES;
import static java.util.function.Function.identity;

/**
 * Base service implementation. It contains the Querydsl factory for epostrx and epostrx_workflow
 * databases.
 *
 * <p>This base class also provides common functions to be used by subclasses.
 */
public abstract class BaseServiceImpl {
    /**
     * The join flag ' WITH (NOLOCK) '.
     */
    protected static final String JOIN_FLAG_NOLOCK = " WITH (NOLOCK) ";

    /**
     * SQLQueryFactory for epostrx database. Initialized in constructor and will never change. It is a
     * protected field so that can be accessed by subclasses easily.
     */
    protected static SQLQueryFactory epostrxQueryFactory = null;

    /**
     * SQLQueryFactory for epostrx_workflow database. Initialized in constructor and will never
     * change. It is a protected field so that can be accessed by subclasses easily.
     */
    protected static SQLQueryFactory workflowQueryFactory = null;

    /**
     * Constructor with datasources.
     *
     * @param epostrxDataSource  DataSource for epostrx database. Required
     * @param workflowDataSource DataSource for epostrx_workflow database. Required
     * @throws RuntimeException if epostrxDataSource or workflowDataSource is null
     */
    protected BaseServiceImpl(DataSource epostrxDataSource, DataSource workflowDataSource) {
        Helper.checkNull(epostrxDataSource, "epostrxDataSource");
        Helper.checkNull(workflowDataSource, "workflowDataSource");

        if (BaseServiceImpl.epostrxQueryFactory == null) {
            synchronized (SQLQueryFactory.class) {
                if (BaseServiceImpl.epostrxQueryFactory == null) {
                    Configuration configuration = new Configuration(SQLServer2012Templates.DEFAULT);
                    BaseServiceImpl.epostrxQueryFactory =
                            new SQLQueryFactory(configuration, epostrxDataSource);
                    BaseServiceImpl.workflowQueryFactory =
                            new SQLQueryFactory(configuration, workflowDataSource);
                }
            }
        }
    }

    /**
     * Get RX defaults.
     *
     * <p>This method will try to get from Infinispan cache before get from database.
     *
     * @return CRxDefaults model
     */
    protected CRxDefaults getRxDefaults() {
        // Load from cache
        CRxDefaults rxDefaults =
                InfinispanCache.get(CacheName.RX_DEFAULTS, CacheName.RX_DEFAULTS.name());
        if (rxDefaults != null) {
            return rxDefaults;
        }

        // Load from database
        QRxDefaults qRxDefaults = QRxDefaults.rxDefaults;
        rxDefaults =
                epostrxQueryFactory
                        .select(
                                Projections.bean(
                                        CRxDefaults.class,
                                        qRxDefaults.maxPvDays,
                                        qRxDefaults.otcAsRxPrecheck,
                                        qRxDefaults.precheckAll,
                                        qRxDefaults.pwoPrecheck,
                                        qRxDefaults.checkNarcoticRefills))
                        .from(qRxDefaults)
                        .fetchFirst();

        // Put to cache
        InfinispanCache.put(CacheName.RX_DEFAULTS, CacheName.RX_DEFAULTS.name(), rxDefaults);
        return rxDefaults == null ? new CRxDefaults() : rxDefaults;
    }

    /**
     * Get code value status by given keyword.
     *
     * <p>This method will try to get from Infinispan cache before get from database.
     *
     * @param keyword The code value keyword. Required
     * @return code value status. May be null if not found
     * @throws RuntimeException if the keyword is null/empty
     */
    protected String getCodeValueStatus(String keyword) {
        Helper.checkString(keyword, "keyword");

        // Load from cache
        String status = InfinispanCache.get(CacheName.CODE_VALUE_STATUS, keyword);
        if (status != null) {
            return status;
        }

        // Load from database
        QCodeValue qCodeValue = QCodeValue.codeValue;
        status =
                epostrxQueryFactory
                        .select(qCodeValue.statusFlag)
                        .from(qCodeValue)
                        .where(qCodeValue.codeValueKeyword.eq(keyword))
                        .fetchFirst();

        // Put to cache
        InfinispanCache.put(CacheName.CODE_VALUE_STATUS, keyword, status);
        return status;
    }

    /**
     * Get trading partner type of given trading partner num.
     *
     * <p>This method will try to get from Infinispan cache before get from database.
     *
     * @param tpNum The trading partner number. Required
     * @return Trading partner type. May be null if not found
     * @throws RuntimeException if the tpNum is null
     */
    protected String getTradingPartnerType(Long tpNum) {
        Helper.checkNull(tpNum, "tpNum");

        // Load from cache
        String type = InfinispanCache.get(CacheName.TRADING_PARTNER_TYPES, tpNum);
        if (type != null) {
            return type;
        }

        // Load from database
        QTradingPartnerGeneral qTradingPartnerGeneral = QTradingPartnerGeneral.tradingPartnerGeneral;
        QTradingPartnerTypes qTradingPartnerTypes = QTradingPartnerTypes.tradingPartnerTypes;

        type =
                epostrxQueryFactory
                        .select(qTradingPartnerTypes.tradingPartnerTypeDesc)
                        .from(qTradingPartnerTypes)
                        .join(qTradingPartnerGeneral)
                        .on(
                                qTradingPartnerGeneral.tradingPartnerTypeId.eq(
                                        qTradingPartnerTypes.tradingPartnerTypeId))
                        .where(qTradingPartnerGeneral.tradingPartnerNum.eq(tpNum))
                        .fetchFirst();
        // Put to cache
        InfinispanCache.put(CacheName.TRADING_PARTNER_TYPES, tpNum, type);
        return type;
    }

    /**
     * Get workflow queue names by queue numbers.
     *
     * <p>This method will try to get from Infinispan cache before get from database.
     *
     * @param queueNums List of workflow queue numbers. Required to be non-empty
     * @return Workflow queue names. Key is queue number, value is queue name.
     */
    protected Map<Integer, String> getWorkflowQueueNames(List<Integer> queueNums) {
        Map<Integer, String> result = new HashMap<>();

        // Load from cache
        List<CWorkflowQueue> queues = InfinispanCache.getWorkflowQueues(queueNums);
        if (queues != null && !queues.isEmpty()) {
            queues.forEach(
                    queue -> result.put(queue.getWorkflowQueueNum(), queue.getWorkflowQueueName()));
        }

        // Remaining queueNums to be loaded from database
        queueNums = new ArrayList<>(queueNums);
        queueNums.removeAll(result.keySet());

        if (!queueNums.isEmpty()) {
            QWorkflowQueueList qWorkflowQueueList = QWorkflowQueueList.workflowQueueList;
            QWorkflowQueueNames qWorkflowQueueNames = QWorkflowQueueNames.workflowQueueNames;

            // Get queues from database
            queues =
                    workflowQueryFactory
                            .select(
                                    Projections.bean(
                                            CWorkflowQueue.class,
                                            qWorkflowQueueList.workflowQueueNum,
                                            qWorkflowQueueList.tradingPartnerNum,
                                            qWorkflowQueueNames.workflowQueueName))
                            .from(qWorkflowQueueList)
                            .join(qWorkflowQueueNames)
                            .on(
                                    qWorkflowQueueList.workflowQueueNameId.eq(
                                            qWorkflowQueueNames.workflowQueueNameId))
                            .where(qWorkflowQueueList.workflowQueueNum.in(queueNums))
                            .fetch();

            for (CWorkflowQueue queue : queues) {
                result.put(queue.getWorkflowQueueNum(), queue.getWorkflowQueueName());
            }

            // Put to cache
            InfinispanCache.putAll(
                    WORKFLOW_QUEUES,
                    queues.stream()
                            .collect(Collectors.toMap(CWorkflowQueue::getWorkflowQueueNum, identity())));
        }

        return result;
    }

    /**
     * Get workflow queue numbers by names and trading partner num. Refer to original
     * F_getWorkflowQueueNumbers function.
     *
     * <p>This method will try to get from Infinispan cache before get from database.
     *
     * @param queueNames List of workflow queue names. Required to be non-empty
     * @param tpNum      The trading partner number. May be null.
     * @return Queue numbers map. Key is queue name, value is list of queue numbers
     * @throws RuntimeException if the queueNames is null/empty
     */
    protected Map<String, List<Integer>> getWorkflowQueueNumbers(
            List<String> queueNames, Long tpNum) {
        Helper.checkList(queueNames, "queueNames");

        Map<String, List<Integer>> result = new HashMap<>();

        // Load from cache
        List<CWorkflowQueue> queues = InfinispanCache.getWorkflowQueues(queueNames, tpNum);
        if (queues != null && !queues.isEmpty()) {
            queues.forEach(
                    queue ->
                            result
                                    .computeIfAbsent(queue.getWorkflowQueueName(), (key) -> new ArrayList<>())
                                    .add(queue.getWorkflowQueueNum()));
        }

        // Remaining queueNames to be loaded from database
        queueNames = new ArrayList<>(queueNames);
        queueNames.removeAll(result.keySet());

        if (!queueNames.isEmpty()) {

            QWorkflowQueueList qWorkflowQueueList = QWorkflowQueueList.workflowQueueList;
            QWorkflowQueueNames qWorkflowQueueNames = QWorkflowQueueNames.workflowQueueNames;

            BooleanExpression conditions = qWorkflowQueueNames.workflowQueueName.in(queueNames);
            if (tpNum != null) {
                conditions = conditions.and(qWorkflowQueueList.tradingPartnerNum.eq(tpNum));
            }

            // Get queues from database
            queues =
                    workflowQueryFactory
                            .select(
                                    Projections.bean(
                                            CWorkflowQueue.class,
                                            qWorkflowQueueList.workflowQueueNum,
                                            qWorkflowQueueList.tradingPartnerNum,
                                            qWorkflowQueueNames.workflowQueueName))
                            .from(qWorkflowQueueList)
                            .join(qWorkflowQueueNames)
                            .on(
                                    qWorkflowQueueList.workflowQueueNameId.eq(
                                            qWorkflowQueueNames.workflowQueueNameId))
                            .where(conditions)
                            .fetch();

            for (CWorkflowQueue queue : queues) {
                result
                        .computeIfAbsent(queue.getWorkflowQueueName(), (key) -> new ArrayList<>())
                        .add(queue.getWorkflowQueueNum());
            }

            // Put to cache
            InfinispanCache.putAll(
                    CacheName.WORKFLOW_QUEUES,
                    queues.stream()
                            .collect(Collectors.toMap(CWorkflowQueue::getWorkflowQueueNum, Function.identity())));
        }
        return result;
    }

    /**
     * Get single workflow queue number by given queue name and trading partner num.
     *
     * <p>This method will try to get from Infinispan cache before get from database.
     *
     * @param queueName The workflow queue name. Required
     * @param tpNum     The trading partner number. May be null.
     * @return The workflow queue number. May be null if not found
     * @throws RuntimeException if the queueName is null/empty
     */
    protected Integer getWorkflowQueueNumber(String queueName, Long tpNum) {
        Helper.checkString(queueName, "queueName");

        Map<String, List<Integer>> result =
                getWorkflowQueueNumbers(Collections.singletonList(queueName), tpNum);
        return result.isEmpty() ? null : result.values().iterator().next().get(0);
    }

    /**
     * Get rules by order based rule.
     *
     * <p>This method will try to get from Infinispan cache before get from database.
     *
     * @param orderBasedRule The order based rule. Required to be non-empty
     * @return List of rule ids.
     */
    protected List<String> getRules(String orderBasedRule) {
        List<String> result = new ArrayList<>();

        // Load from cache
        List<CRule> rules = InfinispanCache.getRules(orderBasedRule);
        if (rules != null && !rules.isEmpty()) {
            rules.forEach(r -> result.add(r.getRuleId().toString()));
        }

        if (rules == null || rules.isEmpty()) {
            QRules qRules = QRules.rules;

            // Get rules from database
            rules =
                    workflowQueryFactory
                            .select(Projections.bean(CRule.class, qRules.ruleId, qRules.orderBasedRule))
                            .from(qRules)
                            .where(qRules.orderBasedRule.eq(orderBasedRule))
                            .fetch();

            for (CRule rule : rules) {
                result.add(rule.getRuleId().toString());
            }

            // Put to cache
            InfinispanCache.putAll(
                    RULES, rules.stream().collect(Collectors.toMap(CRule::getRuleId, identity())));
        }

        return result;
    }

    /**
     * Get code values by category id.
     *
     * <p>This method will try to get from Infinispan cache before get from database.
     *
     * @param fkCodeCatId The category id. Required to be non-empty
     * @return List if code value keywords.
     */
    protected List<String> getCodeValues(String fkCodeCatId) {
        List<String> result = new ArrayList<>();
        List<CCodeValue> codeValues = getCodeValueModels(fkCodeCatId);
        if (codeValues != null && !codeValues.isEmpty()) {
            codeValues.forEach(r -> result.add(r.getCodeValueKeyword()));
        }
        return result;
    }

    /**
     * Get code value models by category id.
     *
     * <p>This method will try to get from Infinispan cache before get from database.
     *
     * @param fkCodeCatId The category id. Required to be non-empty
     * @return List of code value models.
     */
    protected List<CCodeValue> getCodeValueModels(String fkCodeCatId) {
        // Load from cache
        List<CCodeValue> codeValues = InfinispanCache.getCodeValues(fkCodeCatId);

        if (codeValues == null || codeValues.isEmpty()) {
            QCodeValue qCodeValue = QCodeValue.codeValue;
            // Get code values from database
            codeValues =
                    epostrxQueryFactory
                            .select(
                                    Projections.bean(
                                            CCodeValue.class,
                                            qCodeValue.applicationName,
                                            qCodeValue.codeValueDesc,
                                            qCodeValue.codeValueId,
                                            qCodeValue.codeValueKeyword,
                                            qCodeValue.createTs,
                                            qCodeValue.fkCodeCatId,
                                            qCodeValue.lastUpdtBy,
                                            qCodeValue.lastUpdtTs,
                                            qCodeValue.restartRequired,
                                            qCodeValue.statusFlag))
                            .from(qCodeValue)
                            .where(qCodeValue.fkCodeCatId.eq(fkCodeCatId))
                            .fetch();
            // Put to cache
            InfinispanCache.putAll(
                    CODE_VALUES,
                    codeValues.stream().collect(Collectors.toMap(CCodeValue::getCodeValueId, identity())));
        }
        return codeValues;
    }

    /**
     * Get Rx escripts by script id.
     *
     * @param scriptId The script ID. Required.
     * @return List of RxEScriptDTO.
     * @throws RuntimeException if the scriptId is null
     */
    protected List<RxEScriptDTO> getRxEScripts(BigInteger scriptId) {
        Helper.checkNull(scriptId, "scriptId");

        QEScriptMsgAttributes qEScriptMsgAttributes = QEScriptMsgAttributes.eScriptMsgAttributes;
        return getSqlQueryForRxEScripts()
                .where(qEScriptMsgAttributes.eScriptMsgAttributeSeq.eq(scriptId))
                .fetch();
    }

    /**
     * Get Rx escripts by rxNumber.
     *
     * @param rxNumber The RxNumber. Required
     * @return List of RxEScriptDTO.
     * @throws RuntimeException if the rxNumber is null/empty
     */
    protected List<RxEScriptDTO> getRxEScripts(String rxNumber) {
        Helper.checkString(rxNumber, "rxNumber");

        QEScriptMsgAttributes qeScriptMsgAttributes = QEScriptMsgAttributes.eScriptMsgAttributes;
        return getSqlQueryForRxEScripts().where(qeScriptMsgAttributes.rxNumber.eq(rxNumber)).fetch();
    }

    /**
     * Get rx status code description of given rx status code number.
     *
     * <p>This method will try to get from Infinispan cache before get from database.
     *
     * @param rxStatusCodeNum The rx status code number. Required
     * @return The rx status code description. May be null if not found
     * @throws RuntimeException if the rxStatusCodeNum is null
     */
    protected String getRxStatusCode(Byte rxStatusCodeNum) {
        Helper.checkNull(rxStatusCodeNum, "rxStatusCodeNum");

        // Load from cache
        String rxStatusCodeDesc = InfinispanCache.get(CacheName.RX_STATUS_CODE, rxStatusCodeNum);
        if (rxStatusCodeDesc != null) {
            return rxStatusCodeDesc;
        }

        // Load from database
        QRxStatusCodes qRxStatusCodes = QRxStatusCodes.rxStatusCodes;

        rxStatusCodeDesc =
                epostrxQueryFactory
                        .select(qRxStatusCodes.rxStatusCodeDesc)
                        .from(qRxStatusCodes)
                        .where(qRxStatusCodes.rxStatusCodeNum.eq(rxStatusCodeNum))
                        .fetchFirst();
        // Put to cache
        if (rxStatusCodeDesc != null) {
            InfinispanCache.put(CacheName.RX_STATUS_CODE, rxStatusCodeNum, rxStatusCodeDesc);
        }
        return rxStatusCodeDesc;
    }

    /**
     * Get order defaults.
     *
     * <p>This method will try to get from Infinispan cache before get from database.
     *
     * @return COrderDefaults model
     */
    protected COrderDefaults getOrderDefaults() {
        // Load from cache
        COrderDefaults orderDefaults =
                InfinispanCache.get(CacheName.ORDER_DEFAULTS, CacheName.ORDER_DEFAULTS.name());
        if (orderDefaults != null) {
            return orderDefaults;
        }

        // Load from database
        QOrderDefaults qOrderDefaults = QOrderDefaults.orderDefaults;
        orderDefaults =
                epostrxQueryFactory
                        .select(Projections.bean(COrderDefaults.class, qOrderDefaults.familyMode))
                        .from(qOrderDefaults)
                        .addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                        .fetchFirst();

        // Put to cache
        InfinispanCache.put(CacheName.ORDER_DEFAULTS, CacheName.ORDER_DEFAULTS.name(), orderDefaults);
        return orderDefaults == null ? new COrderDefaults() : orderDefaults;
    }

    /**
     * Get all of shipping method.
     *
     * <p>This method will try to get from Infinispan cache before get from database.
     *
     * @return List of CShippingMethod.
     */
    protected List<CShippingMethod> getShippingMethods() {
        // Load from cache
        List<CShippingMethod> shippingMethods =
                InfinispanCache.get(CacheName.SHIPPING_METHODS, CacheName.SHIPPING_METHODS.name());
        if (shippingMethods != null) {
            return shippingMethods;
        }

        // Load from database
        QShippingMethods qShippingMethods = QShippingMethods.shippingMethods;
        shippingMethods =
                epostrxQueryFactory
                        .select(
                                Projections.bean(
                                        CShippingMethod.class,
                                        qShippingMethods.shipMethodId,
                                        qShippingMethods.shipMethodDesc))
                        .from(qShippingMethods)
                        .addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                        .fetch();

        // Put to cache
        InfinispanCache.put(
                CacheName.SHIPPING_METHODS, CacheName.SHIPPING_METHODS.name(), shippingMethods);
        return shippingMethods;
    }

    /**
     * Get rsRxLinking parent and child.
     * Code from file: F_getRxFillsRemaining.sql
     *
     * @param rxNumber The rx number
     * @param active   The active
     * @return The list of RsMultiLinkDTO
     */
    protected List<RsMultiLinkDTO> getRsRxLinkingParentChild(String rxNumber, String active) {
        Helper.checkString(rxNumber, "rxNumber");
        Helper.checkString(active, "active");

        // Code from file: F_RS_RXLINKING_GETPARENTCHILD.sql
        QRxMultiLink qRxMultiLink = QRxMultiLink.rxMultiLink;
        QSystemUsers qSystemUsers = QSystemUsers.systemUsers;
        SQLQuery<RsMultiLinkDTO> query = epostrxQueryFactory.select(Projections.bean(RsMultiLinkDTO.class,
                        qRxMultiLink.rxLinkSeq,
                        qRxMultiLink.childRxNumber,
                        qRxMultiLink.parentRxNumber,
                        qRxMultiLink.parentEScriptMsgAttributeSeq,
                        qRxMultiLink.childEScriptMsgAttributeSeq,
                        qRxMultiLink.active,
                        qRxMultiLink.createdSysUserNum,
                        qSystemUsers.sysUserLogin,
                        qRxMultiLink.createdDatetime,
                        qRxMultiLink.deactivatedSysUserNum,
                        qRxMultiLink.deactivatedDatetime,
                        qRxMultiLink.comment
                ))
                .from(qRxMultiLink).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .join(qSystemUsers).addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .on(qSystemUsers.sysUserNum.eq(qRxMultiLink.createdSysUserNum));
        if (!"All".equals(active)) {
            query = query.where(qRxMultiLink.active.eq(active));
        }
        List<RsMultiLinkDTO> rsMultiLinkDTOList = query.distinct().fetch();
        List<RsMultiLinkDTO> result = new ArrayList<>();
        result.addAll(rsMultiLinkDTOList.stream()
                .filter(r -> r.getParentRxNumber().equals(rxNumber) || r.getChildRxNumber().equals(rxNumber))
                .collect(Collectors.toList()));
        //Line: 64-124
        while (true) {
            List<String> parentNum = result.stream().map(r -> r.getParentRxNumber()).collect(Collectors.toList());
            List<String> childNum = result.stream().map(r -> r.getChildRxNumber()).collect(Collectors.toList());
            List<RsMultiLinkDTO> parents = rsMultiLinkDTOList.stream()
                    .filter(r -> parentNum.contains(r.getChildRxNumber()) && !childNum.contains(r.getChildRxNumber()))
                    .collect(Collectors.toList());
            List<String> newParentNum = parents.stream().map(r -> r.getParentRxNumber()).collect(Collectors.toList());
            List<RsMultiLinkDTO> children = rsMultiLinkDTOList.stream()
                    .filter(r -> childNum.contains(r.getParentRxNumber()) &&
                            !parentNum.contains(r.getParentRxNumber()) &&
                            !newParentNum.contains(r.getParentRxNumber()))
                    .collect(Collectors.toList());
            if (parents.isEmpty() && children.isEmpty()) {
                break;
            }
            result.addAll(parents);
            result.addAll(children);
        }
        return result;
    }

    /**
     * Generate SQL query to fetch RxEScripts
     *
     * @return SQLQuery to fetch Rx EScripts
     */
    private SQLQuery<RxEScriptDTO> getSqlQueryForRxEScripts() {
        QEScriptMsgAttributes qEScriptMsgAttributes = QEScriptMsgAttributes.eScriptMsgAttributes;
        QProducts qProducts = QProducts.products;
        QOKHydrocodoneSanityProducts qOKHydrocodoneSanityProducts =
                QOKHydrocodoneSanityProducts.OKHydrocodoneSanityProducts;
        QRxFillAux qRxFillAux = QRxFillAux.rxFillAux;
        return epostrxQueryFactory
                .select(
                        Projections.bean(
                                RxEScriptDTO.class,
                                qEScriptMsgAttributes.eScriptMsgAttributeSeq.as("eScriptMsgAttributeSeq"),
                                qEScriptMsgAttributes.rxRefillsLeft.as("rxRefillsLeft"),
                                qEScriptMsgAttributes.writtenQuantity.as("writtenQuantity"),
                                qEScriptMsgAttributes.dispensedQuantity.as("dispensedQuantity"),
                                qEScriptMsgAttributes.rxNumber.as("rxNumber"),
                                qEScriptMsgAttributes.originalNumRefills.as("originalNumRefills"),
                                qEScriptMsgAttributes.numRefills.as("numRefills"),
                                qEScriptMsgAttributes.rxQtyLeft.as("rxQtyLeft"),
                                qRxFillAux.fillStatusNum.as("fillStatusNum"),
                                qRxFillAux.fillQtyDispensed.as("fillQtyDispensed"),
                                qRxFillAux.ortfQty.as("ortfQty"),
                                qProducts.prodDea.as("prodDea")))
                .from(qEScriptMsgAttributes)
                .addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .leftJoin(qProducts)
                .addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .on(qProducts.prodId.eq(qEScriptMsgAttributes.dispensedProductId))
                .leftJoin(qRxFillAux)
                .addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .on(qRxFillAux.eScriptMsgAttributeSeq.eq(qEScriptMsgAttributes.eScriptMsgAttributeSeq))
                .leftJoin(qOKHydrocodoneSanityProducts)
                .addJoinFlag(JOIN_FLAG_NOLOCK, JoinFlag.Position.BEFORE_CONDITION)
                .on(qOKHydrocodoneSanityProducts.gpi.eq(qProducts.prodGenericRefNum));
    }
}
