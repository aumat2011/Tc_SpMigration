package com.humana.pharmacy.demo.controller;

import com.humana.pharmacy.demo.dto.GenericResponse;
import com.humana.pharmacy.dto.AnsDefaultRxPlanParamsResult;
import com.humana.pharmacy.dto.AnsRxProductResult;
import com.humana.pharmacy.dto.RsRxLinkWorkflowMinUtilizationCheckResult;
import com.humana.pharmacy.service.AnsDefaultRxPlanParamsService;
import com.humana.pharmacy.service.AnsRxProductService;
import com.humana.pharmacy.service.RsRxLinkWorkflowMinUtilizationCheckService;
import com.humana.pharmacy.service.RsRxLinkingInsertService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

import static java.util.Optional.ofNullable;

/**
 * A demo controller for phase2.
 */
@RestController
@RequestMapping("/phase2/methods")
@Slf4j
public class Phase2Controller {

    /**
     * The RsRxLinkingInsertService service.
     */
    @Autowired
    private RsRxLinkingInsertService rsRxLinkingInsertService;
    /**
     * The AnsDefaultRxPlanParamsService service.
     */
    @Autowired
    private AnsDefaultRxPlanParamsService ansDefaultRxPlanParamsService;
    /**
     * The RsRxLinkWorkflowMinUtilizationCheckService service.
     */
    @Autowired
    private RsRxLinkWorkflowMinUtilizationCheckService rsRxLinkWorkflowMinUtilizationCheckService;
    /**
     * The AnsRxProductService service.
     */
    @Autowired
    private AnsRxProductService ansRxProductService;

    /**
     * Insert RsRxLinking.
     *
     * @param scriptId            the eScriptMsgAttributeSeq
     * @param userNum             the userNum
     * @param mode                the mode
     * @param deactivationComment the deactivationComment
     * @param flag                the flag
     * @return the response
     */
    @GetMapping("/insertRsRxLinking")
    public GenericResponse insertRsRxLinking(
            @RequestParam(required = false) BigInteger scriptId,
            @RequestParam(required = false) Long userNum,
            @RequestParam(required = false) String mode,
            @RequestParam(required = false) String deactivationComment,
            @RequestParam(required = false) String flag) {
        log.debug(
                "insertRsRxLinking called with scriptId: {},  userNum: {}, mode: {}, deactivationComment: {}, flag: {}",
                new Object[]{scriptId, userNum, mode, deactivationComment, flag});
        try {
            rsRxLinkingInsertService.insertRsRxLinking(
                    scriptId, userNum, mode, deactivationComment, flag);
            return GenericResponse.OK("");
        } catch (Exception e) {
            e.printStackTrace();
            return GenericResponse.ERROR(e.getMessage());
        }
    }

    /**
     * Check Rs Rx link workflow min utilization.
     *
     * @param rxNumber The rx number.
     * @return the response
     */
    @GetMapping("/checkRsRxLinkWorkflowMinUtilization")
    public GenericResponse checkRsRxLinkWorkflowMinUtilization(@RequestHeader String rxNumber) {
        log.debug("checkRsRxLinkWorkflowMinUtilization called with rxNumber: " + rxNumber);
        try {
            RsRxLinkWorkflowMinUtilizationCheckResult res =
                    rsRxLinkWorkflowMinUtilizationCheckService.checkRsRxLinkWorkflowMinUtilization(rxNumber);
            return GenericResponse.OK(res == null ? "" : res.toErrorMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return GenericResponse.ERROR(e.getMessage());
        }
    }

    /**
     * Get the Rx/Plan Parameters.
     *
     * @param prodId   The prod id. Optional
     * @param ppNum    The pp num. Optional
     * @param scriptId The script id. Optional
     * @return the response
     */
    @GetMapping("/getAnsDefaultRxPlanParams")
    public GenericResponse getAnsDefaultRxPlanParams(
            @RequestHeader(required = false) Long prodId,
            @RequestHeader(required = false) Long ppNum,
            @RequestHeader(required = false) BigInteger scriptId) {
        log.debug(
                "getAnsDefaultRxPlanParams called with prodId: "
                        + prodId
                        + " ppNum: "
                        + ppNum
                        + " scriptId: "
                        + scriptId);
        try {
            AnsDefaultRxPlanParamsResult result = new AnsDefaultRxPlanParamsResult();
            ansDefaultRxPlanParamsService.getAnsDefaultRxPlanParams(prodId, ppNum, scriptId);
            StringBuilder sb = new StringBuilder();
            if (result != null) {
                sb.append("narcoticCode: ")
                        .append(result.getNarcoticCode())
                        .append(",")
                        .append("numRefillsAllowed: ")
                        .append(result.getNumRefillsAllowed())
                        .append(",")
                        .append("numDaysFromOrigDate: ")
                        .append(result.getNumDaysFromOrigDate())
                        .append(",")
                        .append("numDaysExpire: ")
                        .append(result.getNumDaysExpire())
                        .append(",")
                        .append("maxDaysSupply: ")
                        .append(result.getMaxDaysSupply())
                        .append(",")
                        .append("minPctUtil: ")
                        .append(result.getMinPctUtil())
                        .append(",")
                        .append("acceptFax: ")
                        .append(result.getAcceptFax())
                        .append(",")
                        .append("planNumRefillsAllowed: ")
                        .append(result.getPlanNumRefillsAllowed())
                        .append(",")
                        .append("planMinPctUtil: ")
                        .append(result.getPlanMinPctUtil())
                        .append(",")
                        .append("planMaxPctUtil: ")
                        .append(result.getPlanMaxPctUtil())
                        .append(",")
                        .append("planMinQuantity: ")
                        .append(result.getPlanMinQuantity())
                        .append(",")
                        .append("planMaxQuantity: ")
                        .append(result.getPlanMaxQuantity())
                        .append(",")
                        .append("planMinDaysSupply: ")
                        .append(result.getPlanMinDaysSupply())
                        .append(",")
                        .append("planMaxDaysSupply: ")
                        .append(result.getPlanMaxDaysSupply())
                        .append(",")
                        .append("future1: ")
                        .append(result.getFuture1())
                        .append(",")
                        .append("future2: ")
                        .append(result.getFuture2())
                        .append(",")
                        .append("future3: ")
                        .append(result.getFuture3())
                        .append(",")
                        .append("future4: ")
                        .append(result.getFuture4())
                        .append(",")
                        .append("future5: ")
                        .append(result.getFuture5())
                        .append(",")
                        .append("future6: ")
                        .append(result.getFuture6())
                        .append(",")
                        .append("future7: ")
                        .append(result.getFuture7());
            }
            return GenericResponse.OK(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return GenericResponse.ERROR(e.getMessage());
        }
    }

    /**
     * Check ansRxProduct.
     *
     * @param scriptId The script id. Optional
     * @return the response
     */
    @GetMapping("/checkAnsRxProduct")
    public GenericResponse checkAnsRxProduct(@RequestParam(required = false) BigInteger scriptId) {
        log.debug("checkAnsRxProduct called with scriptId: " + scriptId);
        try {
            AnsRxProductResult result = ansRxProductService.checkAnsRxProduct(scriptId);
            return GenericResponse.OK(ofNullable(result).map(r -> r.toErrorMessage()).orElse(""));
        } catch (Exception e) {
            e.printStackTrace();
            return GenericResponse.ERROR(e.getMessage());
        }
    }
}
