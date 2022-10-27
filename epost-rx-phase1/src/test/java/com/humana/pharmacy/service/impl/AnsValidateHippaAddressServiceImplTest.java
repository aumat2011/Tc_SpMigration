package com.humana.pharmacy.service.impl;

import com.humana.pharmacy.TestsHelper;
import com.humana.pharmacy.dto.AnsValidateHippaPatientDTO;
import com.humana.pharmacy.common.cache.model.COrderDefaults;
import com.humana.pharmacy.common.cache.model.CShippingMethod;
import com.humana.pharmacy.common.entity.QPatientPlans;
import com.humana.pharmacy.common.service.impl.FunctionsServiceImpl;
import com.humana.pharmacy.dto.AnsValidateHippaAddressResult;
import com.humana.pharmacy.dto.AnsValidateHippaFamilyDTO;
import com.humana.pharmacy.dto.AnsValidateHippaOrderDTO;
import com.humana.pharmacy.dto.AnsValidateHippaScriptDTO;
import com.querydsl.core.JoinFlag;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Predicate;
import com.querydsl.sql.SQLQuery;
import com.querydsl.sql.SQLQueryFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.sql.DataSource;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * Unit test cases for AnsValidateHippaAddressServiceImpl;
 */
@SuppressWarnings("unchecked")
@ExtendWith(MockitoExtension.class)
public class AnsValidateHippaAddressServiceImplTest {
    /**
     * The number '32 * 24 * 60 * 60 * 1000'.
     */
    private static final long GREATER_THAN_ONE_MONTH = 32L * 24 * 60 * 60 * 1000;

    /**
     * The "epostrx" DataSource instance.
     */
    @Mock
    private DataSource epostrxDataSource;

    /**
     * The "epostrx_workflow" DataSource instance.
     */
    @Mock
    private DataSource workflowDataSource;

    /**
     * The SQLQuery instance.
     */
    @Mock
    private SQLQuery query;

    /**
     * The FunctionsServiceImpl instance.
     */
    private FunctionsServiceImpl functionsService;

    /**
     * The AnsValidateHippaAddressServiceImpl instance.
     */
    private AnsValidateHippaAddressServiceImpl ansRxNarcCodeService;

    /**
     * The order num.
     */
    private BigInteger orderNum = BigInteger.valueOf(1);

    /**
     * The tuple.
     */
    @Mock
    private Tuple tuple;

    /**
     * The AnsValidateHippaOrderDTO list.
     */
    private List<AnsValidateHippaOrderDTO> ansValidateHippaList;

    /**
     * The AnsValidateHippaScriptDTO list.
     */
    private List<AnsValidateHippaScriptDTO> scriptList;

    /**
     * The AnsValidateHippaFamilyDTO list.
     */
    private List<AnsValidateHippaFamilyDTO> familyList;

    /**
     * The AnsValidateHippaPatientDTO list.
     */
    private List<AnsValidateHippaPatientDTO> patientList;

    /**
     * The shipping methods.
     */
    private List<CShippingMethod> shippingMethods;

    /**
     * The order defaults.
     */
    private COrderDefaults orderDefaults;

    /**
     * The 'epostrx' query factory.
     */
    @Mock
    private SQLQueryFactory epostrxQueryFactory;

    /**
     * The 'epostrx_workflow' query factory.
     */
    @Mock
    private SQLQueryFactory workflowQueryFactory;

    @BeforeEach
    public void setUp() {
        functionsService = new FunctionsServiceImpl(epostrxDataSource, workflowDataSource);
        ansRxNarcCodeService = new AnsValidateHippaAddressServiceImpl(epostrxDataSource, workflowDataSource);
        ansRxNarcCodeService.setFunctionsService(functionsService);

        TestsHelper.setFieldValue(ansRxNarcCodeService, "epostrxQueryFactory", epostrxQueryFactory);
        TestsHelper.setFieldValue(ansRxNarcCodeService, "workflowQueryFactory", workflowQueryFactory);

        ansValidateHippaList = getAnsValidateHippaOrderDTOs();
        scriptList = getAnsValidateHippaScriptDTOs();
        familyList = getAnsValidateHippaFamilyDTOs();
        patientList = getAnsValidateHippaPatientDTOs();
        shippingMethods = getShippingMethods();
        orderDefaults = getOrderDefaults();
    }

    private void setUpMock() {
        when(query.from(any(Expression.class))).thenReturn(query);
        when(query.leftJoin(any(EntityPath.class))).thenReturn(query);
        when(query.on(any(Predicate.class))).thenReturn(query);
        when(query.where(any(Predicate.class))).thenReturn(query);

        when(query.addJoinFlag(any(String.class), any(JoinFlag.Position.class))).thenReturn(query);

        when(epostrxQueryFactory.select(any(Expression.class))).thenReturn(query);
    }

    @Test
    public void test_ctor_epostrxDataSourceNull() {
        assertThrows(RuntimeException.class, () -> new RxSanityServiceImpl(null, workflowDataSource));
    }

    @Test
    public void test_ctor_workflowDataSourceNull() {
        assertThrows(RuntimeException.class, () -> new RxSanityServiceImpl(epostrxDataSource, null));
    }

    @Test
    public void test_checkAnsValidateHippaAddress_orderNumNull() {
        assertThrows(RuntimeException.class, () -> ansRxNarcCodeService.checkAnsValidateHippaAddress(null));
    }

    @Test
    public void test_checkAnsValidateHippaAddress_functionsServiceNull() {
        ansRxNarcCodeService = new AnsValidateHippaAddressServiceImpl(epostrxDataSource, workflowDataSource);
        assertThrows(RuntimeException.class, () -> ansRxNarcCodeService.checkAnsValidateHippaAddress(orderNum));
    }

    @Test
    public void test_checkAnsValidateHippaAddress_noFamilyId1() {
        ansValidateHippaList.get(0).setVerified(false);
        ansValidateHippaList.get(0).setOverrideReasonId((byte) 3);
        ansValidateHippaList.get(0).setLastVerification(new Timestamp(System.currentTimeMillis()));
        ansValidateHippaList.get(0).setActive("Y");
        ansValidateHippaList.get(0).setFamilyId("1");

        scriptList.get(0).setPfFamilyId(scriptList.get(0).getFamilyId());
        scriptList.get(0).setPaPatientNum(scriptList.get(0).getPatientNum());

        setUpMock();

        when(query.fetch()).thenReturn(ansValidateHippaList, shippingMethods, scriptList, familyList, patientList);
        when(query.fetchFirst()).thenReturn(orderDefaults);

        assertThrows(RuntimeException.class, () -> ansRxNarcCodeService.checkAnsValidateHippaAddress(orderNum));
    }

    @Test
    public void test_checkAnsValidateHippaAddress_noFamilyId2() {
        ansValidateHippaList.get(0).setVerified(true);
        ansValidateHippaList.get(0).setOverrideReasonId((byte) 3);
        ansValidateHippaList.get(0).setLastVerification(new Timestamp(System.currentTimeMillis()));
        ansValidateHippaList.get(0).setActive("Y");
        ansValidateHippaList.get(0).setFamilyId("1");

        scriptList.get(0).setPfFamilyId(scriptList.get(0).getFamilyId());
        scriptList.get(0).setPaPatientNum(scriptList.get(0).getPatientNum());
        scriptList.get(0).setPatientDob(new Timestamp(System.currentTimeMillis() + GREATER_THAN_ONE_MONTH));
        scriptList.get(0).setGeneralStatusCode("A");
        scriptList.get(0).setOtcPpnum(1L);
        scriptList.get(0).setPpNum(2L);
        scriptList.get(0).setRelationshipNum(1);

        patientList.get(0).setFamilyId(null);
        patientList.get(1).setFamilyId(null);

        setUpMock();

        when(query.fetch()).thenReturn(ansValidateHippaList, shippingMethods, scriptList, familyList, patientList);
        when(query.fetchFirst()).thenReturn(orderDefaults);

        assertThrows(RuntimeException.class, () -> ansRxNarcCodeService.checkAnsValidateHippaAddress(orderNum));
    }

    @Test
    public void test_checkAnsValidateHippaAddress_orderNumNotFound() {
        setUpMock();

        orderNum = BigInteger.valueOf(9999);
        AnsValidateHippaAddressResult res = ansRxNarcCodeService.checkAnsValidateHippaAddress(orderNum);
        assertTrue(res.getErrors().size() > 0);
    }

    @Test
    public void test_checkAnsValidateHippaAddress_CurrierNotUsps1() {
        ansValidateHippaList.get(0).setPatientAddress("general 123 delivery");
        shippingMethods.get(0).setShipMethodDesc("FEDEX 345");

        setUpMock();

        when(query.fetch()).thenReturn(ansValidateHippaList, shippingMethods);

        AnsValidateHippaAddressResult res = ansRxNarcCodeService.checkAnsValidateHippaAddress(orderNum);
        assertTrue(res.getErrors().size() > 0);
        assertTrue(res.toErrorMessage().contains("CANNOT BE SEND TO GENERAL DELIVERY ADDRESS BECAUSE CURRIER IS NOT USPS."));
    }

    @Test
    public void test_checkAnsValidateHippaAddress_CurrierNotUsps2() {
        ansValidateHippaList.get(0).setPatientAddress2("general 123 delivery");
        shippingMethods.get(0).setShipMethodDesc("UPS 345");

        setUpMock();

        when(query.fetch()).thenReturn(ansValidateHippaList, shippingMethods);

        AnsValidateHippaAddressResult res = ansRxNarcCodeService.checkAnsValidateHippaAddress(orderNum);
        assertTrue(res.getErrors().size() > 0);
        assertTrue(res.toErrorMessage().contains("CANNOT BE SEND TO GENERAL DELIVERY ADDRESS BECAUSE CURRIER IS NOT USPS."));
    }

    @Test
    public void test_checkAnsValidateHippaAddress_CurrierNotUsps3() {
        ansValidateHippaList.get(0).setPatientAddress("general 123 delivery");
        shippingMethods.get(0).setShipMethodDesc("BEST 1 METHOD 2 OVERNIGHT 3b DEL");

        setUpMock();

        when(query.fetch()).thenReturn(ansValidateHippaList, shippingMethods);

        AnsValidateHippaAddressResult res = ansRxNarcCodeService.checkAnsValidateHippaAddress(orderNum);
        assertTrue(res.getErrors().size() > 0);
        assertTrue(res.toErrorMessage().contains("CANNOT BE SEND TO GENERAL DELIVERY ADDRESS BECAUSE CURRIER IS NOT USPS."));
    }

    @Test
    public void test_checkAnsValidateHippaAddress_CurrierNotUsps4() {
        ansValidateHippaList.get(0).setPatientAddress("general 123 delivery");
        shippingMethods.get(0).setShipMethodDesc("BEST 1 METHOD 2 OVERNIGHT 3b NO 4x CHARGE");

        setUpMock();

        when(query.fetch()).thenReturn(ansValidateHippaList, shippingMethods);

        AnsValidateHippaAddressResult res = ansRxNarcCodeService.checkAnsValidateHippaAddress(orderNum);
        assertTrue(res.getErrors().size() > 0);
        assertTrue(res.toErrorMessage().contains("CANNOT BE SEND TO GENERAL DELIVERY ADDRESS BECAUSE CURRIER IS NOT USPS."));
    }

    @Test
    public void test_checkAnsValidateHippaAddress_AddressNotVerifiedNotOverridden() {
        ansValidateHippaList.get(0).setVerified(false);
        ansValidateHippaList.get(0).setOverrideReasonId((byte) 0);

        setUpMock();

        when(query.fetch()).thenReturn(ansValidateHippaList, shippingMethods);

        AnsValidateHippaAddressResult res = ansRxNarcCodeService.checkAnsValidateHippaAddress(orderNum);
        assertTrue(res.getErrors().size() > 0);
        assertTrue(res.toErrorMessage().contains("SHIP ADDRESS IS NOT USPS VERIFIED AND NOT OVERRIDDEN."));
    }

    @Test
    public void test_checkAnsValidateHippaAddress_AddressNotVerifiedServiceDown1() {
        ansValidateHippaList.get(0).setVerified(false);
        ansValidateHippaList.get(0).setOverrideReasonId((byte) 3);

        setUpMock();

        when(query.fetch()).thenReturn(ansValidateHippaList, shippingMethods);

        AnsValidateHippaAddressResult res = ansRxNarcCodeService.checkAnsValidateHippaAddress(orderNum);
        assertTrue(res.getErrors().size() > 0);
        assertTrue(res.toErrorMessage().contains("SHIP ADDRESS IS NOT USPS VERIFIED. ENTERPRISE ADDRESS SERVICE IS DOWN."));
    }

    @Test
    public void test_checkAnsValidateHippaAddress_AddressNotVerifiedServiceDown2() {
        ansValidateHippaList.get(0).setVerified(false);
        ansValidateHippaList.get(0).setOverrideReasonId((byte) 3);
        ansValidateHippaList.get(0).setLastVerification(new Timestamp(System.currentTimeMillis() - GREATER_THAN_ONE_MONTH));

        setUpMock();

        when(query.fetch()).thenReturn(ansValidateHippaList, shippingMethods);

        AnsValidateHippaAddressResult res = ansRxNarcCodeService.checkAnsValidateHippaAddress(orderNum);
        assertTrue(res.getErrors().size() > 0);
        assertTrue(res.toErrorMessage().contains("SHIP ADDRESS IS NOT USPS VERIFIED. ENTERPRISE ADDRESS SERVICE IS DOWN."));
    }

    @Test
    public void test_checkAnsValidateHippaAddress_AddressLineOvered1() {
        ansValidateHippaList.get(0).setVerified(false);
        ansValidateHippaList.get(0).setOverrideReasonId((byte) 3);
        ansValidateHippaList.get(0).setLastVerification(new Timestamp(System.currentTimeMillis()));
        ansValidateHippaList.get(0).setPatientAddress("123456789012345678901234567890123456");

        setUpMock();

        when(query.fetch()).thenReturn(ansValidateHippaList, shippingMethods);

        AnsValidateHippaAddressResult res = ansRxNarcCodeService.checkAnsValidateHippaAddress(orderNum);
        assertTrue(res.getErrors().size() > 0);
        assertTrue(res.toErrorMessage().contains("ADDRESS LINE IS OVER 35 CHARACTERS."));
    }

    @Test
    public void test_checkAnsValidateHippaAddress_AddressLineOvered2() {
        ansValidateHippaList.get(0).setVerified(false);
        ansValidateHippaList.get(0).setOverrideReasonId((byte) 3);
        ansValidateHippaList.get(0).setLastVerification(new Timestamp(System.currentTimeMillis()));
        ansValidateHippaList.get(0).setPatientAddress(null);
        ansValidateHippaList.get(0).setPatientAddress2("123456789012345678901234567890123456");

        setUpMock();

        when(query.fetch()).thenReturn(ansValidateHippaList, shippingMethods);

        AnsValidateHippaAddressResult res = ansRxNarcCodeService.checkAnsValidateHippaAddress(orderNum);
        assertTrue(res.getErrors().size() > 0);
        assertTrue(res.toErrorMessage().contains("ADDRESS LINE IS OVER 35 CHARACTERS."));
    }

    @Test
    public void test_checkAnsValidateHippaAddress_ManualVerificationRequired1() {
        ansValidateHippaList.get(0).setVerified(false);
        ansValidateHippaList.get(0).setOverrideReasonId((byte) 3);
        ansValidateHippaList.get(0).setLastVerification(new Timestamp(System.currentTimeMillis()));
        ansValidateHippaList.get(0).setPatientAddress(null);
        ansValidateHippaList.get(0).setPatientAddress2(null);
        ansValidateHippaList.get(0).setPatientShipAddressSeq(null);

        setUpMock();

        when(query.fetch()).thenReturn(ansValidateHippaList, shippingMethods);

        AnsValidateHippaAddressResult res = ansRxNarcCodeService.checkAnsValidateHippaAddress(orderNum);
        assertTrue(res.getErrors().size() > 0);
        assertTrue(res.toErrorMessage().contains("MANUAL VERIFICATION REQUIRED FOR FREE-TEXT ADDRESS ENTRY."));
    }

    @Test
    public void test_checkAnsValidateHippaAddress_ManualVerificationRequired2() {
        ansValidateHippaList.get(0).setVerified(false);
        ansValidateHippaList.get(0).setOverrideReasonId((byte) 3);
        ansValidateHippaList.get(0).setLastVerification(new Timestamp(System.currentTimeMillis()));
        ansValidateHippaList.get(0).setPatientShipAddressSeq(0L);

        setUpMock();

        when(query.fetch()).thenReturn(ansValidateHippaList, shippingMethods);

        AnsValidateHippaAddressResult res = ansRxNarcCodeService.checkAnsValidateHippaAddress(orderNum);
        assertTrue(res.getErrors().size() > 0);
        assertTrue(res.toErrorMessage().contains("MANUAL VERIFICATION REQUIRED FOR FREE-TEXT ADDRESS ENTRY."));
    }

    @Test
    public void test_checkAnsValidateHippaAddress_ManualVerificationRequired3() {
        ansValidateHippaList.clear();

        setUpMock();

        when(query.fetch()).thenReturn(ansValidateHippaList, shippingMethods);

        AnsValidateHippaAddressResult res = ansRxNarcCodeService.checkAnsValidateHippaAddress(orderNum);
        assertTrue(res.getErrors().size() > 0);
        assertTrue(res.toErrorMessage().contains("MANUAL VERIFICATION REQUIRED FOR FREE-TEXT ADDRESS ENTRY."));
    }

    @Test
    public void test_checkAnsValidateHippaAddress_AddressStatusInactive() {
        ansValidateHippaList.get(0).setVerified(false);
        ansValidateHippaList.get(0).setOverrideReasonId((byte) 3);
        ansValidateHippaList.get(0).setLastVerification(new Timestamp(System.currentTimeMillis()));
        ansValidateHippaList.get(0).setActive("N");

        setUpMock();

        when(query.fetch()).thenReturn(ansValidateHippaList, shippingMethods);

        AnsValidateHippaAddressResult res = ansRxNarcCodeService.checkAnsValidateHippaAddress(orderNum);
        assertTrue(res.getErrors().size() > 0);
        assertTrue(res.toErrorMessage().contains("SHIP TO ADDRESS STATUS IS INACTIVE."));
    }

    @Test
    public void test_checkAnsValidateHippaAddress_NoFamilyRecord() {
        ansValidateHippaList.get(0).setVerified(false);
        ansValidateHippaList.get(0).setOverrideReasonId((byte) 3);
        ansValidateHippaList.get(0).setLastVerification(new Timestamp(System.currentTimeMillis()));
        ansValidateHippaList.get(0).setActive("Y");
        ansValidateHippaList.get(0).setFamilyId(null);

        setUpMock();

        when(query.fetch()).thenReturn(ansValidateHippaList, shippingMethods);
        when(query.fetchFirst()).thenReturn(orderDefaults);

        AnsValidateHippaAddressResult res = ansRxNarcCodeService.checkAnsValidateHippaAddress(orderNum);
        assertTrue(res.getErrors().size() > 0);
        assertTrue(res.toErrorMessage().contains("Family Mode and Patient Has No Family Record "));
    }

    @Test
    public void test_checkAnsValidateHippaAddress_PatientNotValid() {
        ansValidateHippaList.get(0).setVerified(false);
        ansValidateHippaList.get(0).setOverrideReasonId((byte) 3);
        ansValidateHippaList.get(0).setLastVerification(new Timestamp(System.currentTimeMillis()));
        ansValidateHippaList.get(0).setActive("Y");
        ansValidateHippaList.get(0).setFamilyId("1");

        scriptList.get(0).setPfFamilyId("XX");
        scriptList.get(0).setPaPatientNum(scriptList.get(0).getPatientNum());

        setUpMock();

        when(query.fetch()).thenReturn(ansValidateHippaList, shippingMethods, scriptList);
        when(query.fetchFirst()).thenReturn(orderDefaults);

        AnsValidateHippaAddressResult res = ansRxNarcCodeService.checkAnsValidateHippaAddress(orderNum);
        assertTrue(res.getErrors().size() > 0);
        assertTrue(res.toErrorMessage().contains("A patient on this order is *NOT* valid. (please verify all members)."));
    }

    @Test
    public void test_checkAnsValidateHippaAddress_NoMinorAddress1() {
        ansValidateHippaList.get(0).setVerified(false);
        ansValidateHippaList.get(0).setOverrideReasonId((byte) 3);
        ansValidateHippaList.get(0).setLastVerification(new Timestamp(System.currentTimeMillis()));
        ansValidateHippaList.get(0).setActive("Y");
        ansValidateHippaList.get(0).setFamilyId("1");

        scriptList.get(0).setPfFamilyId(scriptList.get(0).getFamilyId());
        scriptList.get(0).setPaPatientNum(scriptList.get(0).getPatientNum());
        scriptList.get(0).setPatientDob(new Timestamp(System.currentTimeMillis() + GREATER_THAN_ONE_MONTH));
        scriptList.get(0).setGeneralStatusCode("A");
        scriptList.get(0).setOtcPpnum(1L);
        scriptList.get(0).setPpNum(2L);
        scriptList.get(0).setRelationshipNum(1);

        setUpMock();

        when(query.fetch()).thenReturn(ansValidateHippaList, shippingMethods, scriptList, familyList, patientList);
        when(query.fetchFirst()).thenReturn(orderDefaults);

        AnsValidateHippaAddressResult res = ansRxNarcCodeService.checkAnsValidateHippaAddress(orderNum);
        assertTrue(res.getErrors().size() > 0);
        assertTrue(res.toErrorMessage().contains("Addr Error.  Minor Address Not Found on Cardholder Profile "));
    }

    @Test
    public void test_checkAnsValidateHippaAddress_NoMinorAddress2() {
        ansValidateHippaList.get(0).setVerified(false);
        ansValidateHippaList.get(0).setOverrideReasonId((byte) 3);
        ansValidateHippaList.get(0).setLastVerification(new Timestamp(System.currentTimeMillis()));
        ansValidateHippaList.get(0).setActive("Y");
        ansValidateHippaList.get(0).setFamilyId("1");

        scriptList.get(0).setPfFamilyId(scriptList.get(0).getFamilyId());
        scriptList.get(0).setPaPatientNum(scriptList.get(0).getPatientNum());
        scriptList.get(0).setPatientDob(new Timestamp(System.currentTimeMillis() + GREATER_THAN_ONE_MONTH));
        scriptList.get(0).setGeneralStatusCode("A");
        scriptList.get(0).setOtcPpnum(1L);
        scriptList.get(0).setPpNum(2L);
        scriptList.get(0).setRelationshipNum(1);

        familyList.get(0).setHeadOfHousehold(null);

        patientList.get(1).setPatientNum(patientList.get(0).getPatientNum());
        patientList.get(0).setPatientNum(BigInteger.valueOf(3));

        setUpMock();

        when(tuple.get(QPatientPlans.patientPlans.ppNum)).thenReturn(2L);
        when(epostrxQueryFactory.select(any(Expression.class), any(Expression.class))).thenReturn(query);
        when(query.join(any(EntityPath.class))).thenReturn(query);
        when(query.fetch()).thenReturn(ansValidateHippaList, shippingMethods, scriptList, familyList,
                Arrays.asList(tuple), Arrays.asList(tuple), Arrays.asList(tuple), Arrays.asList(tuple), patientList);
        when(query.fetchFirst()).thenReturn(orderDefaults);

        AnsValidateHippaAddressResult res = ansRxNarcCodeService.checkAnsValidateHippaAddress(orderNum);
        assertTrue(res.getErrors().size() > 0);
        assertTrue(res.toErrorMessage().contains("Addr Error.  Minor Address Not Found on Cardholder Profile "));
    }

    @Test
    public void test_checkAnsValidateHippaAddress_NoMinorAddress3() {
        ansValidateHippaList.get(0).setVerified(false);
        ansValidateHippaList.get(0).setOverrideReasonId((byte) 3);
        ansValidateHippaList.get(0).setLastVerification(new Timestamp(System.currentTimeMillis()));
        ansValidateHippaList.get(0).setActive("Y");
        ansValidateHippaList.get(0).setFamilyId("1");

        scriptList.get(0).setPfFamilyId(scriptList.get(0).getFamilyId());
        scriptList.get(0).setPaPatientNum(scriptList.get(0).getPatientNum());
        scriptList.get(0).setPatientDob(new Timestamp(System.currentTimeMillis() - 30 * 12 * GREATER_THAN_ONE_MONTH));
        scriptList.get(0).setGeneralStatusCode("A");
        scriptList.get(0).setOtcPpnum(1L);
        scriptList.get(0).setPpNum(2L);
        scriptList.get(0).setRelationshipNum(1);

        familyList.get(0).setHeadOfHousehold(null);

        patientList.get(1).setPatientNum(patientList.get(0).getPatientNum());
        patientList.get(0).setPatientDob(null);

        setUpMock();

        when(tuple.get(QPatientPlans.patientPlans.ppNum)).thenReturn(2L);
        when(epostrxQueryFactory.select(any(Expression.class), any(Expression.class))).thenReturn(query);
        when(query.join(any(EntityPath.class))).thenReturn(query);
        when(query.fetch()).thenReturn(ansValidateHippaList, shippingMethods, scriptList, familyList,
                Arrays.asList(tuple), Arrays.asList(tuple), Arrays.asList(tuple), Arrays.asList(tuple), patientList);
        when(query.fetchFirst()).thenReturn(orderDefaults);

        AnsValidateHippaAddressResult res = ansRxNarcCodeService.checkAnsValidateHippaAddress(orderNum);
        assertTrue(res.getErrors().size() > 0);
        assertTrue(res.toErrorMessage().contains("Addr Error.  Minor Address Not Found on Cardholder Profile "));
    }

    @Test
    public void test_checkAnsValidateHippaAddress_NoMinorAddress4() {
        ansValidateHippaList.get(0).setVerified(false);
        ansValidateHippaList.get(0).setOverrideReasonId((byte) 3);
        ansValidateHippaList.get(0).setLastVerification(new Timestamp(System.currentTimeMillis()));
        ansValidateHippaList.get(0).setActive("Y");
        ansValidateHippaList.get(0).setFamilyId("X");

        scriptList.get(0).setPfFamilyId(scriptList.get(0).getFamilyId());
        scriptList.get(0).setPaPatientNum(scriptList.get(0).getPatientNum());
        scriptList.get(0).setPatientDob(new Timestamp(System.currentTimeMillis() - 30 * 12 * GREATER_THAN_ONE_MONTH));
        scriptList.get(0).setGeneralStatusCode("A");
        scriptList.get(0).setOtcPpnum(1L);
        scriptList.get(0).setPpNum(2L);
        scriptList.get(0).setRelationshipNum(1);

        familyList.clear();

        setUpMock();

        when(query.fetch()).thenReturn(ansValidateHippaList, shippingMethods, scriptList, familyList, patientList);
        when(query.fetchFirst()).thenReturn(orderDefaults);

        AnsValidateHippaAddressResult res = ansRxNarcCodeService.checkAnsValidateHippaAddress(orderNum);
        assertTrue(res.getErrors().size() > 0);
        assertTrue(res.toErrorMessage().contains("Addr Error.  Minor Address Not Found on Cardholder Profile "));
    }

    @Test
    public void test_checkAnsValidateHippaAddress_NoMinorAddress5() {
        ansValidateHippaList.get(0).setVerified(false);
        ansValidateHippaList.get(0).setOverrideReasonId((byte) 3);
        ansValidateHippaList.get(0).setLastVerification(new Timestamp(System.currentTimeMillis()));
        ansValidateHippaList.get(0).setActive("Y");
        ansValidateHippaList.get(0).setFamilyId("X");

        scriptList.get(0).setPfFamilyId(scriptList.get(0).getFamilyId());
        scriptList.get(0).setPaPatientNum(scriptList.get(0).getPatientNum());
        scriptList.get(0).setPatientDob(new Timestamp(System.currentTimeMillis()));
        scriptList.get(0).setGeneralStatusCode("A");
        scriptList.get(0).setOtcPpnum(1L);
        scriptList.get(0).setPpNum(2L);
        scriptList.get(0).setRelationshipNum(1);

        familyList.clear();

        patientList.get(0).setActive("N");
        patientList.get(1).setAddressTypeNum(1);

        setUpMock();

        when(query.fetch()).thenReturn(ansValidateHippaList, shippingMethods, scriptList, familyList, patientList);
        when(query.fetchFirst()).thenReturn(orderDefaults);

        AnsValidateHippaAddressResult res = ansRxNarcCodeService.checkAnsValidateHippaAddress(orderNum);
        assertTrue(res.getErrors().size() > 0);
        assertTrue(res.toErrorMessage().contains("Addr Error.  Minor Address Not Found on Cardholder Profile "));
    }

    @Test
    public void test_checkAnsValidateHippaAddress_NoMinorAddress6() {
        ansValidateHippaList.get(0).setVerified(false);
        ansValidateHippaList.get(0).setOverrideReasonId((byte) 3);
        ansValidateHippaList.get(0).setLastVerification(new Timestamp(System.currentTimeMillis()));
        ansValidateHippaList.get(0).setActive("Y");
        ansValidateHippaList.get(0).setFamilyId("1");

        scriptList.get(0).setPfFamilyId(scriptList.get(0).getFamilyId());
        scriptList.get(0).setPaPatientNum(scriptList.get(0).getPatientNum());
        scriptList.get(0).setPatientDob(new Timestamp(System.currentTimeMillis() - 30 * 12 * GREATER_THAN_ONE_MONTH));
        scriptList.get(0).setGeneralStatusCode("A");
        scriptList.get(0).setOtcPpnum(1L);
        scriptList.get(0).setPpNum(2L);
        scriptList.get(0).setRelationshipNum(1);

        scriptList.get(1).setFamilyId(null);
        scriptList.get(2).setPatientNum(null);

        familyList.get(0).setHeadOfHousehold(null);
        familyList.get(1).setPpNum(null);

        setUpMock();

        when(tuple.get(QPatientPlans.patientPlans.ppNum)).thenReturn(2L);
        when(epostrxQueryFactory.select(any(Expression.class), any(Expression.class))).thenReturn(query);
        when(query.join(any(EntityPath.class))).thenReturn(query);
        when(query.fetch()).thenReturn(ansValidateHippaList, shippingMethods, scriptList, familyList,
                Arrays.asList(tuple), Arrays.asList(tuple), patientList);
        when(query.fetchFirst()).thenReturn(orderDefaults);

        AnsValidateHippaAddressResult res = ansRxNarcCodeService.checkAnsValidateHippaAddress(orderNum);
        assertTrue(res.getErrors().size() > 0);
        assertTrue(res.toErrorMessage().contains(
                "Addr Error.  Minor Address Not Found on Cardholder Profile "));
    }

    @Test
    public void test_checkAnsValidateHippaAddress_NoMinorAddress7() {
        ansValidateHippaList.get(0).setVerified(false);
        ansValidateHippaList.get(0).setOverrideReasonId((byte) 4);
        ansValidateHippaList.get(0).setLastVerification(new Timestamp(System.currentTimeMillis()));
        ansValidateHippaList.get(0).setActive("Y");
        ansValidateHippaList.get(0).setFamilyId("1");

        scriptList.get(0).setPfFamilyId(scriptList.get(0).getFamilyId());
        scriptList.get(0).setPaPatientNum(scriptList.get(0).getPatientNum());
        scriptList.get(0).setPatientDob(new Timestamp(System.currentTimeMillis() - 30 * 12 * GREATER_THAN_ONE_MONTH));
        scriptList.get(0).setGeneralStatusCode("A");
        scriptList.get(0).setOtcPpnum(1L);
        scriptList.get(0).setPpNum(2L);
        scriptList.get(0).setRelationshipNum(1);

        scriptList.get(1).setOrderStatusNum((byte) 7);
        scriptList.get(2).setOrderLineStatusNum((byte) 7);

        familyList.get(0).setHeadOfHousehold(null);
        familyList.get(1).setPpNum(2L);

        setUpMock();

        when(tuple.get(QPatientPlans.patientPlans.ppNum)).thenReturn(2L);
        when(epostrxQueryFactory.select(any(Expression.class), any(Expression.class))).thenReturn(query);
        when(query.join(any(EntityPath.class))).thenReturn(query);
        when(query.fetch()).thenReturn(ansValidateHippaList, shippingMethods, scriptList, familyList,
                Arrays.asList(tuple), patientList);
        when(query.fetchFirst()).thenReturn(orderDefaults);

        AnsValidateHippaAddressResult res = ansRxNarcCodeService.checkAnsValidateHippaAddress(orderNum);
        assertTrue(res.getErrors().size() > 0);
        assertTrue(res.toErrorMessage().contains("Addr Error.  Minor Address Not Found on Cardholder Profile "));
    }

    @Test
    public void test_checkAnsValidateHippaAddress_NoMinorAddress8() {
        ansValidateHippaList.get(0).setVerified(null);
        ansValidateHippaList.get(0).setOverrideReasonId((byte) 3);
        ansValidateHippaList.get(0).setLastVerification(new Timestamp(System.currentTimeMillis()));
        ansValidateHippaList.get(0).setActive("Y");
        ansValidateHippaList.get(0).setFamilyId("X");

        scriptList.get(0).setPfFamilyId(scriptList.get(0).getFamilyId());
        scriptList.get(0).setPaPatientNum(scriptList.get(0).getPatientNum());
        scriptList.get(0).setPatientDob(new Timestamp(System.currentTimeMillis()));
        scriptList.get(0).setGeneralStatusCode("A");
        scriptList.get(0).setOtcPpnum(1L);
        scriptList.get(0).setPpNum(2L);
        scriptList.get(0).setRelationshipNum(1);

        familyList.clear();

        patientList.get(0).setCszZipNum(null);
        patientList.get(1).setActive("N");

        setUpMock();

        when(query.fetch()).thenReturn(ansValidateHippaList, shippingMethods, scriptList, familyList, patientList);
        when(query.fetchFirst()).thenReturn(orderDefaults);

        AnsValidateHippaAddressResult res = ansRxNarcCodeService.checkAnsValidateHippaAddress(orderNum);
        assertTrue(res.getErrors().size() > 0);
        assertTrue(res.toErrorMessage().contains("Addr Error.  Minor Address Not Found on Cardholder Profile "));
    }

    @Test
    public void test_checkAnsValidateHippaAddress_NoMinorAddress9() {
        ansValidateHippaList.get(0).setVerified(false);
        ansValidateHippaList.get(0).setOverrideReasonId(null);
        ansValidateHippaList.get(0).setLastVerification(new Timestamp(System.currentTimeMillis()));
        ansValidateHippaList.get(0).setActive("Y");
        ansValidateHippaList.get(0).setFamilyId("X");

        scriptList.get(0).setPfFamilyId(scriptList.get(0).getFamilyId());
        scriptList.get(0).setPaPatientNum(scriptList.get(0).getPatientNum());
        scriptList.get(0).setPatientDob(new Timestamp(System.currentTimeMillis()));
        scriptList.get(0).setGeneralStatusCode("A");
        scriptList.get(0).setOtcPpnum(1L);
        scriptList.get(0).setPpNum(2L);
        scriptList.get(0).setRelationshipNum(1);

        familyList.clear();

        patientList.get(0).setPatientDob(new Timestamp(System.currentTimeMillis() - 20 * 12 * GREATER_THAN_ONE_MONTH));

        setUpMock();

        when(query.fetch()).thenReturn(ansValidateHippaList, shippingMethods, scriptList, familyList, patientList);
        when(query.fetchFirst()).thenReturn(orderDefaults);

        AnsValidateHippaAddressResult res = ansRxNarcCodeService.checkAnsValidateHippaAddress(orderNum);
        assertTrue(res.getErrors().size() > 0);
        assertTrue(res.toErrorMessage().contains("Addr Error.  Minor Address Not Found on Cardholder Profile "));
    }

    @Test
    public void test_checkAnsValidateHippaAddress_NoMinorAddress10() {
        ansValidateHippaList.get(0).setVerified(false);
        ansValidateHippaList.get(0).setOverrideReasonId((byte) 3);
        ansValidateHippaList.get(0).setLastVerification(new Timestamp(System.currentTimeMillis()));
        ansValidateHippaList.get(0).setActive("Y");
        ansValidateHippaList.get(0).setFamilyId("1");

        scriptList.get(0).setPfFamilyId(scriptList.get(0).getFamilyId());
        scriptList.get(0).setPaPatientNum(scriptList.get(0).getPatientNum());
        scriptList.get(0).setPatientDob(new Timestamp(System.currentTimeMillis() + GREATER_THAN_ONE_MONTH));
        scriptList.get(0).setGeneralStatusCode("A");
        scriptList.get(0).setOtcPpnum(1L);
        scriptList.get(0).setPpNum(2L);
        scriptList.get(0).setRelationshipNum(1);

        familyList.get(0).setPatientNum(null);
        familyList.get(1).setPatientNum(BigInteger.valueOf(1));

        setUpMock();

        when(query.fetch()).thenReturn(ansValidateHippaList, shippingMethods, scriptList, familyList, patientList);
        when(query.fetchFirst()).thenReturn(orderDefaults);

        AnsValidateHippaAddressResult res = ansRxNarcCodeService.checkAnsValidateHippaAddress(orderNum);
        assertTrue(res.getErrors().size() > 0);
        assertTrue(res.toErrorMessage().contains("Addr Error.  Minor Address Not Found on Cardholder Profile "));
    }

    @Test
    public void test_checkAnsValidateHippaAddress_NoMinorAddress11() {
        ansValidateHippaList.get(0).setVerified(false);
        ansValidateHippaList.get(0).setOverrideReasonId((byte) 3);
        ansValidateHippaList.get(0).setLastVerification(new Timestamp(System.currentTimeMillis()));
        ansValidateHippaList.get(0).setActive("Y");
        ansValidateHippaList.get(0).setFamilyId("1");

        scriptList.get(0).setPfFamilyId(scriptList.get(0).getFamilyId());
        scriptList.get(0).setPaPatientNum(scriptList.get(0).getPatientNum());
        scriptList.get(0).setPatientDob(new Timestamp(System.currentTimeMillis() + GREATER_THAN_ONE_MONTH));
        scriptList.get(0).setGeneralStatusCode("A");
        scriptList.get(0).setOtcPpnum(1L);
        scriptList.get(0).setPpNum(2L);
        scriptList.get(0).setRelationshipNum(1);

        familyList.get(0).setHeadOfHousehold(null);
        familyList.get(0).setPatientNum(null);
        familyList.get(0).setCoverageTypeId((byte) 4);
        familyList.get(1).setPatientNum(BigInteger.valueOf(1));

        setUpMock();

        when(tuple.get(QPatientPlans.patientPlans.ppNum)).thenReturn(2L);
        when(epostrxQueryFactory.select(any(Expression.class), any(Expression.class))).thenReturn(query);
        when(query.join(any(EntityPath.class))).thenReturn(query);
        when(query.fetch()).thenReturn(ansValidateHippaList, shippingMethods, scriptList, familyList,
                Arrays.asList(tuple), patientList);
        when(query.fetchFirst()).thenReturn(orderDefaults);

        AnsValidateHippaAddressResult res = ansRxNarcCodeService.checkAnsValidateHippaAddress(orderNum);
        assertTrue(res.getErrors().size() > 0);
        assertTrue(res.toErrorMessage().contains("Addr Error.  Minor Address Not Found on Cardholder Profile "));
    }

    @Test
    public void test_checkAnsValidateHippaAddress_NoMinorAddress12() {
        ansValidateHippaList.get(0).setVerified(false);
        ansValidateHippaList.get(0).setOverrideReasonId((byte) 3);
        ansValidateHippaList.get(0).setLastVerification(new Timestamp(System.currentTimeMillis()));
        ansValidateHippaList.get(0).setActive("Y");
        ansValidateHippaList.get(0).setFamilyId("1");

        scriptList.get(0).setPfFamilyId(scriptList.get(0).getFamilyId());
        scriptList.get(0).setPaPatientNum(scriptList.get(0).getPatientNum());
        scriptList.get(0).setPatientDob(new Timestamp(System.currentTimeMillis() + GREATER_THAN_ONE_MONTH));
        scriptList.get(0).setGeneralStatusCode("A");
        scriptList.get(0).setOtcPpnum(1L);
        scriptList.get(0).setPpNum(2L);
        scriptList.get(0).setRelationshipNum(1);

        familyList.get(1).setHeadOfHousehold("Y");
        familyList.get(0).setPatientNum(null);
        familyList.get(0).setCoverageTypeId((byte) 4);
        familyList.get(1).setPatientNum(BigInteger.valueOf(1));

        setUpMock();

        when(tuple.get(QPatientPlans.patientPlans.ppNum)).thenReturn(2L);
        when(epostrxQueryFactory.select(any(Expression.class), any(Expression.class))).thenReturn(query);
        when(query.join(any(EntityPath.class))).thenReturn(query);
        when(query.fetch()).thenReturn(ansValidateHippaList, shippingMethods, scriptList, familyList,
                Arrays.asList(tuple), patientList);
        when(query.fetchFirst()).thenReturn(orderDefaults);

        AnsValidateHippaAddressResult res = ansRxNarcCodeService.checkAnsValidateHippaAddress(orderNum);
        assertTrue(res.getErrors().size() > 0);
        assertTrue(res.toErrorMessage().contains("Addr Error.  Minor Address Not Found on Cardholder Profile "));
    }

    @Test
    public void test_checkAnsValidateHippaAddress_NoError1() {
        ansValidateHippaList.get(0).setForceShipAddress("Y");

        setUpMock();

        when(query.fetch()).thenReturn(ansValidateHippaList);

        AnsValidateHippaAddressResult res = ansRxNarcCodeService.checkAnsValidateHippaAddress(orderNum);
        assertNull(res);
    }

    @Test
    public void test_checkAnsValidateHippaAddress_NoError2() {
        ansValidateHippaList.get(0).setVerified(false);
        ansValidateHippaList.get(0).setOverrideReasonId((byte) 3);
        ansValidateHippaList.get(0).setLastVerification(new Timestamp(System.currentTimeMillis()));

        orderDefaults.setFamilyMode("N");

        setUpMock();

        when(query.fetch()).thenReturn(ansValidateHippaList, shippingMethods);
        when(query.fetchFirst()).thenReturn(orderDefaults);

        AnsValidateHippaAddressResult res = ansRxNarcCodeService.checkAnsValidateHippaAddress(orderNum);
        assertNull(res);
    }

    @Test
    public void test_checkAnsValidateHippaAddress_NoError3() {
        ansValidateHippaList.get(0).setVerified(false);
        ansValidateHippaList.get(0).setOverrideReasonId((byte) 3);
        ansValidateHippaList.get(0).setLastVerification(new Timestamp(System.currentTimeMillis()));
        ansValidateHippaList.get(0).setActive("Y");
        ansValidateHippaList.get(0).setFamilyId("1");

        setUpMock();

        when(query.fetch()).thenReturn(ansValidateHippaList, shippingMethods, scriptList);
        when(query.fetchFirst()).thenReturn(orderDefaults);

        AnsValidateHippaAddressResult res = ansRxNarcCodeService.checkAnsValidateHippaAddress(orderNum);
        assertNull(res);
    }

    @Test
    public void test_checkAnsValidateHippaAddress_NoError4() {
        ansValidateHippaList.get(0).setVerified(false);
        ansValidateHippaList.get(0).setOverrideReasonId((byte) 3);
        ansValidateHippaList.get(0).setLastVerification(new Timestamp(System.currentTimeMillis()));
        ansValidateHippaList.get(0).setActive("Y");
        ansValidateHippaList.get(0).setFamilyId("1");

        scriptList.get(0).setPfFamilyId(scriptList.get(0).getFamilyId());
        scriptList.get(0).setPaPatientNum(scriptList.get(0).getPatientNum());
        scriptList.get(0).setPatientDob(new Timestamp(System.currentTimeMillis() + GREATER_THAN_ONE_MONTH));
        scriptList.get(0).setGeneralStatusCode("A");
        scriptList.get(0).setOtcPpnum(1L);
        scriptList.get(0).setPpNum(2L);
        scriptList.get(0).setRelationshipNum(1);

        familyList.get(0).setHeadOfHousehold(null);
        familyList.get(0).setPatientNum(BigInteger.valueOf(2));
        familyList.remove(1);

        patientList.get(1).setPatientAddress(patientList.get(0).getPatientAddress());
        patientList.get(1).setPatientAddress2(patientList.get(0).getPatientAddress2());

        setUpMock();

        when(tuple.get(QPatientPlans.patientPlans.ppNum)).thenReturn(2L);
        when(epostrxQueryFactory.select(any(Expression.class), any(Expression.class))).thenReturn(query);
        when(query.join(any(EntityPath.class))).thenReturn(query);
        when(query.fetch()).thenReturn(ansValidateHippaList, shippingMethods, scriptList, familyList,
                Arrays.asList(tuple), patientList);
        when(query.fetchFirst()).thenReturn(orderDefaults);

        AnsValidateHippaAddressResult res = ansRxNarcCodeService.checkAnsValidateHippaAddress(orderNum);
        assertNull(res);
    }

    @Test
    public void test_checkAnsValidateHippaAddress_NoError5() {
        ansValidateHippaList.get(0).setPatientAddress("general 123 delxivery");
        ansValidateHippaList.get(0).setPatientAddress2("general 123 delixvery");
        shippingMethods.get(0).setShipMethodDesc("BEST 1 METHOD 2 OVERNIGHT 3b NO 4x CHARGE");

        ansValidateHippaList.get(0).setVerified(false);
        ansValidateHippaList.get(0).setOverrideReasonId((byte) 3);
        ansValidateHippaList.get(0).setLastVerification(new Timestamp(System.currentTimeMillis()));
        ansValidateHippaList.get(0).setActive("Y");
        ansValidateHippaList.get(0).setFamilyId("1");

        scriptList.get(0).setPfFamilyId(scriptList.get(0).getFamilyId());
        scriptList.get(0).setPaPatientNum(scriptList.get(0).getPatientNum());
        scriptList.get(0).setPatientDob(new Timestamp(System.currentTimeMillis() + GREATER_THAN_ONE_MONTH));
        scriptList.get(0).setGeneralStatusCode("A");
        scriptList.get(0).setOtcPpnum(1L);
        scriptList.get(0).setPpNum(2L);
        scriptList.get(0).setRelationshipNum(1);

        scriptList.get(1).setOrderLineStatusNum((byte) 2);
        scriptList.get(2).setOrderLineStatusNum(null);

        familyList.get(0).setHeadOfHousehold(null);
        familyList.get(0).setPatientNum(BigInteger.valueOf(2));
        familyList.remove(1);

        patientList.get(0).setPatientAddress2(null);
        patientList.get(1).setPatientAddress(patientList.get(0).getPatientAddress());
        patientList.get(1).setPatientAddress2(patientList.get(0).getPatientAddress2());

        setUpMock();

        when(tuple.get(QPatientPlans.patientPlans.ppNum)).thenReturn(2L);
        when(epostrxQueryFactory.select(any(Expression.class), any(Expression.class))).thenReturn(query);
        when(query.join(any(EntityPath.class))).thenReturn(query);
        when(query.fetch()).thenReturn(ansValidateHippaList, shippingMethods, scriptList, familyList,
                Arrays.asList(tuple), patientList);
        when(query.fetchFirst()).thenReturn(orderDefaults);

        AnsValidateHippaAddressResult res = ansRxNarcCodeService.checkAnsValidateHippaAddress(orderNum);
        assertNull(res);
    }

    @Test
    public void test_setFunctionsService_functionsServiceNull() {
        assertThrows(RuntimeException.class, () -> ansRxNarcCodeService.setFunctionsService(null));
    }

    /**
     * Get AnsValidateHippaOrderDTO list.
     *
     * @return the AnsValidateHippaOrderDTO list.
     */
    private List<AnsValidateHippaOrderDTO> getAnsValidateHippaOrderDTOs() {
        List<AnsValidateHippaOrderDTO> list = new ArrayList<>();

        AnsValidateHippaOrderDTO dto1 = new AnsValidateHippaOrderDTO();
        dto1.setShipMethodId(1);
        dto1.setPatientAddress("patientAddress1");
        dto1.setPatientAddress2("patientAddress2");
        dto1.setPatientShipAddressSeq(1L);
        dto1.setPatientAddressSeq(1L);
        dto1.setActive("Y");
        dto1.setConsentAge((byte) 20);
        list.add(dto1);

        AnsValidateHippaOrderDTO dto2 = new AnsValidateHippaOrderDTO();
        dto2.setShipMethodId(2);
        dto2.setPatientAddress("patientAddress1");
        dto2.setPatientAddress2("patientAddress2");
        dto2.setPatientShipAddressSeq(2L);
        dto2.setPatientAddressSeq(2L);
        dto2.setActive("Y");
        dto2.setConsentAge((byte) 20);
        list.add(dto2);

        return list;
    }

    /**
     * Get AnsValidateHippaScriptDTO list.
     *
     * @return the AnsValidateHippaScriptDTO list.
     */
    private List<AnsValidateHippaScriptDTO> getAnsValidateHippaScriptDTOs() {
        List<AnsValidateHippaScriptDTO> list = new ArrayList<>();

        AnsValidateHippaScriptDTO dto1 = new AnsValidateHippaScriptDTO();
        dto1.setFamilyId("1");
        dto1.setPfFamilyId("1");
        dto1.setPatientNum(BigInteger.valueOf(1));
        dto1.setOrderStatusNum((byte) 1);
        dto1.setOrderLineStatusNum((byte) 1);
        dto1.setGeneralStatusCode("B");
        list.add(dto1);

        AnsValidateHippaScriptDTO dto2 = new AnsValidateHippaScriptDTO();
        dto2.setFamilyId("1");
        dto2.setPfFamilyId("1");
        dto2.setPatientNum(BigInteger.valueOf(1));
        dto2.setOrderStatusNum((byte) 1);
        dto2.setOrderLineStatusNum((byte) 1);
        dto2.setGeneralStatusCode("B");
        list.add(dto2);

        AnsValidateHippaScriptDTO dto3 = new AnsValidateHippaScriptDTO();
        dto3.setFamilyId("1");
        dto3.setPfFamilyId("1");
        dto3.setPatientNum(BigInteger.valueOf(1));
        dto3.setOrderStatusNum((byte) 1);
        dto3.setOrderLineStatusNum((byte) 1);
        dto3.setGeneralStatusCode("B");
        list.add(dto3);

        return list;
    }

    /**
     * Get AnsValidateHippaFamilyDTO list.
     *
     * @return the AnsValidateHippaFamilyDTO list.
     */
    private List<AnsValidateHippaFamilyDTO> getAnsValidateHippaFamilyDTOs() {
        List<AnsValidateHippaFamilyDTO> list = new ArrayList<>();

        AnsValidateHippaFamilyDTO dto1 = new AnsValidateHippaFamilyDTO();
        dto1.setHeadOfHousehold("Y");
        dto1.setPatientNum(BigInteger.valueOf(1));
        dto1.setPpNum(1L);
        dto1.setRelationshipNum(1);
        dto1.setCoverageTypeId((byte) 1);
        list.add(dto1);

        AnsValidateHippaFamilyDTO dto2 = new AnsValidateHippaFamilyDTO();
        dto2.setPatientNum(BigInteger.valueOf(2));
        dto2.setPpNum(1L);
        dto2.setRelationshipNum(2);
        dto2.setCoverageTypeId((byte) 1);
        list.add(dto2);

        return list;
    }

    /**
     * Get AnsValidateHippaPatientDTO list.
     *
     * @return the AnsValidateHippaPatientDTO list.
     */
    private List<AnsValidateHippaPatientDTO> getAnsValidateHippaPatientDTOs() {
        List<AnsValidateHippaPatientDTO> list = new ArrayList<>();

        AnsValidateHippaPatientDTO dto1 = new AnsValidateHippaPatientDTO();
        dto1.setActive("Y");
        dto1.setPatientNum(BigInteger.valueOf(1));
        dto1.setFamilyId("1");
        dto1.setPatientDob(new Timestamp(System.currentTimeMillis()));
        dto1.setCszZipNum(1L);
        dto1.setAddressTypeNum(11);
        dto1.setPatientAddrSeq(1L);
        dto1.setPatientAddress("PatientAddress11");
        dto1.setPatientAddress2("PatientAddress12");
        list.add(dto1);

        AnsValidateHippaPatientDTO dto2 = new AnsValidateHippaPatientDTO();
        dto2.setActive("Y");
        dto2.setPatientNum(BigInteger.valueOf(2));
        dto2.setFamilyId("1");
        dto2.setPatientDob(new Timestamp(System.currentTimeMillis()));
        dto2.setCszZipNum(1L);
        dto2.setAddressTypeNum(11);
        dto2.setPatientAddrSeq(1L);
        dto2.setPatientAddress("PatientAddress21");
        dto2.setPatientAddress2("PatientAddress22");
        list.add(dto2);

        return list;
    }

    /**
     * Create list of CShippingMethod.
     *
     * @return the CShippingMethod list
     */
    private static List<CShippingMethod> getShippingMethods() {
        List<CShippingMethod> list = new ArrayList<>();

        list.add(new CShippingMethod(1, "desc1"));
        list.add(new CShippingMethod(2, "desc2"));

        return list;
    }

    /**
     * Create an instance of COrderDefaults.
     *
     * @return the COrderDefaults instance
     */
    private static COrderDefaults getOrderDefaults() {
        return new COrderDefaults("Y");
    }
}
