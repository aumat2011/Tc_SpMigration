package com.humana.pharmacy.demo.controller;

import com.humana.pharmacy.common.dto.RxEScriptDTO;
import com.humana.pharmacy.common.entity.QRxLinkSubstitutionLog;
import com.humana.pharmacy.common.model.RxLinkSubstitutionLog;
import com.humana.pharmacy.common.service.FunctionsService;
import com.humana.pharmacy.demo.dto.GenericResponse;
import com.humana.pharmacy.dto.AnsRxNarcCodeResult;
import com.humana.pharmacy.dto.AnsRxSanityResult;
import com.humana.pharmacy.dto.AnsValidateHippaAddressResult;
import com.humana.pharmacy.dto.ExceedMaxPVDaysResult;
import com.humana.pharmacy.dto.OrderSanityResult;
import com.humana.pharmacy.dto.RxSanityResult;
import com.humana.pharmacy.service.AnsRxNarcCodeService;
import com.humana.pharmacy.service.AnsRxSanityService;
import com.humana.pharmacy.service.AnsValidateHippaAddressService;
import com.humana.pharmacy.service.DuplicateGPIService;
import com.humana.pharmacy.service.ExceedMaxPVDaysService;
import com.humana.pharmacy.service.OrderSanityService;
import com.humana.pharmacy.service.RsRxLinkingSubstitutionService;
import com.humana.pharmacy.service.RxSanityService;
import com.querydsl.sql.SQLQuery;
import com.querydsl.sql.SQLQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.List;

/**
 * A demo controller for phase1.
 */
@RestController
@RequestMapping("/phase1/methods")
@Slf4j
public class Phase1Controller {

    /**
     * The epostrx query factory.
     */
    @Autowired
    @Qualifier("epostrxQueryFactory")
    private SQLQueryFactory epostrxQueryFactory;

    /**
     * The DuplicateGPIService service.
     */
    @Autowired
    private DuplicateGPIService service;
    /**
     * The FunctionsService service.
     */
    @Autowired
    private FunctionsService functionsService;
    /**
     * The ExceedMaxPVDaysService service.
     */
    @Autowired
    private ExceedMaxPVDaysService exceedMaxPVDaysService;
    /**
     * The OrderSanityService service.
     */
    @Autowired
    private OrderSanityService orderSanityService;
    /**
     * The RxSanityService service.
     */
    @Autowired
    private RxSanityService rxSanityService;
    /**
     * The AnsRxSanityService service.
     */
    @Autowired
    private AnsRxSanityService ansRxSanityService;

    /**
     * The AnsRxNarcCodeService service.
     */
    @Autowired
    private AnsRxNarcCodeService ansRxNarcCodeService;

    /**
     * The AnsValidateHippaAddressService service.
     */
    @Autowired
    private AnsValidateHippaAddressService ansValidateHippaAddressService;

    /**
     * The RsRxLinkingSubstitutionService service.
     */
    @Autowired
    private RsRxLinkingSubstitutionService rsRxLinkingSubstitutionService;

    /**
     * Check order sanity.
     *
     * @param queueToBeUsed The queue to be used
     * @param orderNum      The order num
     * @return the response
     */
    @GetMapping("/checkOrderSanity")
    public GenericResponse checkOrderSanity(
            @RequestHeader BigInteger orderNum, @RequestHeader String queueToBeUsed) {
        log.debug(
                "checkOrderSanity called with orderNum: " + orderNum + ", queueToBeUsed: " + queueToBeUsed);
        try {
            OrderSanityResult res = orderSanityService.checkOrderSanity(orderNum, queueToBeUsed);
            return GenericResponse.OK(res == null ? null : res.toErrorMsgs());
        } catch (Exception e) {
            e.printStackTrace();
            return GenericResponse.ERROR(e.getMessage());
        }
    }

    /**
     * Check where order exceeds max PV days
     *
     * @param orderNum The order num.
     * @return the response
     */
    @GetMapping("/checkExceedMaxPVDays")
    public GenericResponse checkExceedMaxPVDays(@RequestHeader BigInteger orderNum) {
        log.debug("checkExceedMaxPVDays called with orderNum: " + orderNum);
        try {
            ExceedMaxPVDaysResult res = exceedMaxPVDaysService.checkExceedMaxPVDays(orderNum);
            return GenericResponse.OK(res == null ? null : res.toErrorMsgs());
        } catch (Exception e) {
            e.printStackTrace();
            return GenericResponse.ERROR(e.getMessage());
        }
    }

    /**
     * Check duplicate GPI for given patient and order invoice number.
     *
     * @param patientNum      the patient number.
     * @param orderInvoiceNum the order invoice number to check duplicate
     * @return the response
     */
    @GetMapping("/checkDuplicateGPI")
    public GenericResponse checkDuplicateGPI(
            @RequestHeader BigInteger patientNum, @RequestHeader String orderInvoiceNum) {
        log.debug(
                "checkDuplicateGPI called with patientNum: "
                        + patientNum
                        + ", orderInvoiceNum: "
                        + orderInvoiceNum);
        try {
            return GenericResponse.OK(service.checkDuplicateGPI(patientNum, orderInvoiceNum));
        } catch (Exception e) {
            e.printStackTrace();
            return GenericResponse.ERROR(e.getMessage());
        }
    }

    /**
     * Get escripts which are precheck required.
     *
     * @param escriptIds the list of escript ids
     * @return the response
     */
    @GetMapping("/getPrecheckRequired")
    public GenericResponse getPrecheckRequired(@RequestHeader List<BigInteger> escriptIds) {
        log.debug("getPrecheckRequired called with escriptIds: " + escriptIds);
        try {
            return GenericResponse.OK(String.valueOf(functionsService.getPrecheckRequired(escriptIds)));
        } catch (Exception e) {
            e.printStackTrace();
            return GenericResponse.ERROR(e.getMessage());
        }
    }

    /**
     * Get escripts which are first time to fill.
     *
     * @param escriptIds the list of escript ids
     * @return the response
     */
    @GetMapping("/getFirstTimeFill")
    public GenericResponse getFirstTimeFill(@RequestHeader List<BigInteger> escriptIds) {
        log.debug("getFirstTimeFill called with escriptIds: " + escriptIds);
        try {
            return GenericResponse.OK(String.valueOf(functionsService.getFirstTimeFill(escriptIds)));
        } catch (Exception e) {
            e.printStackTrace();
            return GenericResponse.ERROR(e.getMessage());
        }
    }

    /**
     * Get escripts which exceed max PV days.
     *
     * @param escriptIds the list of escript ids
     * @return the response
     */
    @GetMapping("/getExceedMaxPVDays")
    public GenericResponse getExceedMaxPVDays(@RequestHeader List<BigInteger> escriptIds) {
        log.debug("getExceedMaxPVDays called with escriptIds: " + escriptIds);
        try {
            return GenericResponse.OK(String.valueOf(functionsService.getExceedMaxPVDays(escriptIds)));
        } catch (Exception e) {
            e.printStackTrace();
            return GenericResponse.ERROR(e.getMessage());
        }
    }

    /**
     * Get escripts paid.
     *
     * @param escriptIds the list of escript ids
     * @return the response
     */
    @GetMapping("/getPrimaryEcsPaid")
    public GenericResponse getPrimaryEcsPaid(@RequestHeader List<BigInteger> escriptIds) {
        log.debug("getPrimaryEcsPaid called with escriptIds: " + escriptIds);
        try {
            return GenericResponse.OK(String.valueOf(functionsService.getPrimaryEcsPaid(escriptIds)));
        } catch (Exception e) {
            e.printStackTrace();
            return GenericResponse.ERROR(e.getMessage());
        }
    }

    /**
     * Get total number of remaining refills for the specified Rx number.
     *
     * @param rxNumber      The RX number
     * @param rxEScriptDTOs List of RxEScriptDTO
     * @return the response
     */
    @GetMapping("/getRemainingRefills")
    public GenericResponse getRemainingRefills(
            @RequestHeader String rxNumber,
            @RequestHeader(required = false) List<RxEScriptDTO> rxEScriptDTOs) {
        log.debug(
                "getRemainingRefills called with rxNumber: "
                        + rxNumber
                        + ", rxEScriptDTOs： "
                        + rxEScriptDTOs);
        try {
            return GenericResponse.OK(
                    String.valueOf(functionsService.getRemainingRefills(rxNumber, rxEScriptDTOs)));
        } catch (Exception e) {
            e.printStackTrace();
            return GenericResponse.ERROR(e.getMessage());
        }
    }

    /**
     * Get total quantity.
     *
     * @param scriptId      The script id
     * @param rxEScriptDTOs List of RxEScriptDTO
     * @return the response
     */
    @GetMapping("/getRxTotalQty")
    public GenericResponse getRxTotalQty(
            @RequestHeader BigInteger scriptId,
            @RequestHeader(required = false) List<RxEScriptDTO> rxEScriptDTOs) {
        log.debug(
                "getRxTotalQty called with scriptId: " + scriptId + ", rxEScriptDTOs： " + rxEScriptDTOs);
        try {
            return GenericResponse.OK(
                    String.valueOf(functionsService.getRxTotalQty(scriptId, rxEScriptDTOs)));
        } catch (Exception e) {
            e.printStackTrace();
            return GenericResponse.ERROR(e.getMessage());
        }
    }

    /**
     * Get total number of remaining refills for the specified Rx number.
     *
     * @param rxNumber      The RX number
     * @param rxEScriptDTOs List of RxEScriptDTO
     * @return the response
     */
    @GetMapping("/getRxTotalQtyFilled")
    public GenericResponse getRxTotalQtyFilled(
            @RequestHeader String rxNumber,
            @RequestHeader(required = false) List<RxEScriptDTO> rxEScriptDTOs) {
        log.debug(
                "getRxTotalQtyFilled called with rxNumber: "
                        + rxNumber
                        + ", rxEScriptDTOs： "
                        + rxEScriptDTOs);
        try {
            return GenericResponse.OK(
                    String.valueOf(functionsService.getRxTotalQtyFilled(rxNumber, rxEScriptDTOs)));
        } catch (Exception e) {
            e.printStackTrace();
            return GenericResponse.ERROR(e.getMessage());
        }
    }

    /**
     * Get total ORTF quantity filled.
     *
     * @param rxNumber      The RX number
     * @param rxEScriptDTOs List of RxEScriptDTO
     * @return the response
     */
    @GetMapping("/getRxTotalORTFQtyFilled")
    public GenericResponse getRxTotalORTFQtyFilled(
            @RequestHeader String rxNumber,
            @RequestHeader(required = false) List<RxEScriptDTO> rxEScriptDTOs) {
        log.debug(
                "getRxTotalORTFQtyFilled called with rxNumber: "
                        + rxNumber
                        + ", rxEScriptDTOs： "
                        + rxEScriptDTOs);
        try {
            return GenericResponse.OK(
                    String.valueOf(functionsService.getRxTotalORTFQtyFilled(rxNumber, rxEScriptDTOs)));
        } catch (Exception e) {
            e.printStackTrace();
            return GenericResponse.ERROR(e.getMessage());
        }
    }

    /**
     * Get total fills.
     *
     * @param scriptId      The script id
     * @param rxEScriptDTOs List of RxEScriptDTO
     * @return the response
     */
    @GetMapping("/getRxTotalFills")
    public GenericResponse getRxTotalFills(
            @RequestHeader BigInteger scriptId,
            @RequestHeader(required = false) List<RxEScriptDTO> rxEScriptDTOs) {
        log.debug(
                "getRxTotalFills called with scriptId: " + scriptId + ", rxEScriptDTOs： " + rxEScriptDTOs);
        try {
            return GenericResponse.OK(
                    String.valueOf(functionsService.getRxTotalFills(scriptId, rxEScriptDTOs)));
        } catch (Exception e) {
            e.printStackTrace();
            return GenericResponse.ERROR(e.getMessage());
        }
    }

    /**
     * Check Rx sanity.
     *
     * @param scriptId The scriptId.
     * @return the response
     */
    @GetMapping("/checkRxSanity")
    public GenericResponse checkRxSanity(@RequestHeader BigInteger scriptId) {
        log.debug("checkRxSanity called with scriptId: " + scriptId);
        try {
            RxSanityResult res = rxSanityService.checkRxSanity(scriptId, null);
            return GenericResponse.OK(res == null ? "" : res.toErrorMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return GenericResponse.ERROR(e.getMessage());
        }
    }

    /**
     * Check ans Rx sanity.
     *
     * @param scriptId The scriptId.
     * @param rxNumber The rxNumber.
     * @return the response
     */
    @GetMapping("/checkAnsRxSanity")
    public GenericResponse checkAnsRxSanity(
            @RequestHeader BigInteger scriptId, @RequestHeader(required = false) String rxNumber) {
        log.debug("checkAnsRxSanity called with scriptId: " + scriptId);
        try {
            AnsRxSanityResult res = ansRxSanityService.checkAnsRxSanity(scriptId, rxNumber);
            return GenericResponse.OK(res == null ? null : res.toErrorMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return GenericResponse.ERROR(e.getMessage());
        }
    }

    /**
     * Check ans Rx narc code.
     *
     * @param scriptId The scriptId.
     * @param rxNumber The rxNumber.
     * @return the response
     */
    @GetMapping("/checkAnsRxNarcCode")
    public GenericResponse checkAnsRxNarcCode(
            @RequestHeader(required = false) BigInteger scriptId,
            @RequestHeader(required = false) String rxNumber) {
        log.debug("checkAnsRxNarcCode called with scriptId: " + scriptId);
        try {
            AnsRxNarcCodeResult res = ansRxNarcCodeService.checkAnsRxNarcCode(scriptId, rxNumber);
            return GenericResponse.OK(res == null ? "" : res.toErrorMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return GenericResponse.ERROR(e.getMessage());
        }
    }

    /**
     * Check ans validate hippa address.
     *
     * @param orderNum The order num.
     * @return the response
     */
    @GetMapping("/checkAnsValidateHippaAddress")
    public GenericResponse checkAnsValidateHippaAddress(@RequestHeader BigInteger orderNum) {
        log.debug("checkAnsValidateHippaAddress called with orderNum: " + orderNum);
        try {
            AnsValidateHippaAddressResult res =
                    ansValidateHippaAddressService.checkAnsValidateHippaAddress(orderNum);
            return GenericResponse.OK(res == null ? "" : res.toErrorMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return GenericResponse.ERROR(e.getMessage());
        }
    }

    /**
     * Get SubEScriptMsgAttributeSeq.
     *
     * @param scriptId The scriptId.
     * @return the response
     */
    @GetMapping("/getSubEScriptMsgAttributeSeq")
    public GenericResponse getSubEScriptMsgAttributeSeq(@RequestHeader BigInteger scriptId) {
        log.debug("getSubEScriptMsgAttributeSeq called with scriptId: " + scriptId);
        try {
            return GenericResponse.OK(
                    rsRxLinkingSubstitutionService.getSubEScriptMsgAttributeSeq(scriptId).toString());
        } catch (Exception e) {
            e.printStackTrace();
            return GenericResponse.ERROR(e.getMessage());
        }
    }

    /**
     * Get getRsRxSubstitutionLog.
     *
     * @param rxNumber The rxNumber.
     * @return the response
     */
    @GetMapping("/getRsRxSubstitutionLog")
    public List<RxLinkSubstitutionLog> getRsRxSubstitutionLog(
            @RequestParam(required = false) String rxNumber) {
        QRxLinkSubstitutionLog qRxLinkSubstitutionLog = QRxLinkSubstitutionLog.rxLinkSubstitutionLog;
        SQLQuery<RxLinkSubstitutionLog> query =
                epostrxQueryFactory.select(qRxLinkSubstitutionLog).from(qRxLinkSubstitutionLog);
        if (rxNumber != null && !rxNumber.isEmpty()) {
            query = query.where(qRxLinkSubstitutionLog.originalRxNumber.eq(rxNumber));
        }
        return query.fetch();
    }
}
