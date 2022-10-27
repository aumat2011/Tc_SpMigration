package com.humana.pharmacy.service.impl;

import com.humana.pharmacy.TestsHelper;
import com.humana.pharmacy.common.cache.model.CCodeValue;
import com.humana.pharmacy.common.dto.RsMultiLinkDTO;
import com.humana.pharmacy.common.model.RxMultiLink;
import com.humana.pharmacy.common.service.impl.FunctionsServiceImpl;
import com.humana.pharmacy.dto.RsRxLinkingInsertDTO;
import com.querydsl.core.types.Expression;
import com.querydsl.sql.RelationalPath;
import com.querydsl.sql.SQLQuery;
import com.querydsl.sql.SQLQueryFactory;
import com.querydsl.sql.dml.SQLDeleteClause;
import com.querydsl.sql.dml.SQLInsertClause;
import com.querydsl.sql.dml.SQLUpdateClause;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import javax.sql.DataSource;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit test cases for RsRxLinkingInsertServiceImp;
 */
@SuppressWarnings("unchecked")
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class RsRxLinkingInsertServiceImplTest {

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
    @Mock(answer = Answers.RETURNS_SELF)
    private SQLQuery query;

    /**
     * The SQLDeleteClause instance.
     */
    @Mock(answer = Answers.RETURNS_SELF)
    private SQLDeleteClause deleteClause;

    /**
     * The SQLInsertClause instance.
     */
    @Mock(answer = Answers.RETURNS_SELF)
    private SQLInsertClause insertClause;

    /**
     * The SQLUpdateClause instance.
     */
    @Mock(answer = Answers.RETURNS_SELF)
    private SQLUpdateClause updateClause;

    /**
     * The FunctionsServiceImpl instance.
     */
    @Mock
    private FunctionsServiceImpl functionsService;

    /**
     * The RsRxLinkingInsertServiceImpl instance.
     */
    private RsRxLinkingInsertServiceImpl rsRxLinkingInsertService;
    /**
     * The 'epostrx' query factory.
     */
    @Mock
    private SQLQueryFactory epostrxQueryFactory;
    /**
     * The 'epostrx orkflow' query factory.
     */
    @Mock
    private SQLQueryFactory workflowQueryFactory;

    @BeforeEach
    public void setUp() {
        rsRxLinkingInsertService =
                new RsRxLinkingInsertServiceImpl(epostrxDataSource, workflowDataSource);
        rsRxLinkingInsertService.setFunctionsService(functionsService);

        TestsHelper.setFieldValue(rsRxLinkingInsertService, "epostrxQueryFactory", epostrxQueryFactory);
        TestsHelper.setFieldValue(
                rsRxLinkingInsertService, "workflowQueryFactory", workflowQueryFactory);
    }

    @Test
    public void test_ctor_epostrxDataSourceNull() {
        assertThrows(
                RuntimeException.class, () -> new RsRxLinkingInsertServiceImpl(null, workflowDataSource));
    }

    @Test
    public void test_ctor_workflowDataSourceNull() {
        assertThrows(
                RuntimeException.class, () -> new RsRxLinkingInsertServiceImpl(epostrxDataSource, null));
    }

    @Test
    public void test_insertRsRxLinking_scriptIdNull() {
        assertThrows(
                RuntimeException.class,
                () -> rsRxLinkingInsertService.insertRsRxLinking(null, null, null, null, null));
    }

    @Test
    public void test_insertRsRxLinking_userNumNull() {
        when(epostrxQueryFactory.select(any(Expression.class))).thenReturn(query);
        // getCodeValueModels, getRsRxLinkingInsertDTOs
        when(query.fetch()).thenReturn(getCodeValue(), new ArrayList());
        // sysUserNum
        when(query.fetchFirst()).thenReturn(101L);
        rsRxLinkingInsertService.insertRsRxLinking(new BigInteger("1"), null, null, null, null);
        verify(query, times(2)).fetch();
    }

    @Test
    public void test_insertRsRxLinking_modeNull() {
        when(epostrxQueryFactory.select(any(Expression.class))).thenReturn(query);
        // getCodeValueModels, getRsRxLinkingInsertDTOs
        when(query.fetch()).thenReturn(getCodeValue(), new ArrayList());
        // sysUserNum
        when(query.fetchFirst()).thenReturn(101L);
        rsRxLinkingInsertService.insertRsRxLinking(new BigInteger("1"), 1010L, null, null, null);
        verify(query, times(2)).fetch();
    }

    @Test
    public void test_insertRsRxLinking_wrongMode() {
        when(epostrxQueryFactory.select(any(Expression.class))).thenReturn(query);
        // getCodeValueModels, getRsRxLinkingInsertDTOs
        when(query.fetch()).thenReturn(getCodeValue(), new ArrayList());
        // sysUserNum
        when(query.fetchFirst()).thenReturn(101L);
        rsRxLinkingInsertService.insertRsRxLinking(new BigInteger("1"), 1010L, "W", null, null);
        verify(query, times(2)).fetch();
    }

    @Test
    public void test_insertRsRxLinking_rxLinkNotEnable() {
        when(epostrxQueryFactory.select(any(Expression.class))).thenReturn(query);
        when(query.fetch()).thenReturn(new ArrayList());
        rsRxLinkingInsertService.insertRsRxLinking(new BigInteger("1"), 1010L, "I", null, null);
        verify(query, times(1)).fetch();
    }

    @Test
    public void test_insertRsRxLinking_noRsRxLinkingInsertDTO() {
        when(epostrxQueryFactory.select(any(Expression.class))).thenReturn(query);
        // getCodeValueModels, getRsRxLinkingInsertDTOs
        when(query.fetch()).thenReturn(getCodeValue(), new ArrayList());
        // sysUserNum
        when(query.fetchFirst()).thenReturn(101L);
        rsRxLinkingInsertService.insertRsRxLinking(new BigInteger("1"), 1010L, "I", null, null);
        verify(query, times(2)).fetch();
    }

    @Test
    public void test_insertRsRxLinking_REFILLRX() {
        when(epostrxQueryFactory.select(any(Expression.class))).thenReturn(query);
        List<RsRxLinkingInsertDTO> dto = getRsRxLinkingInsertDTOList();
        dto.get(0).setEdiTransactionCode("REFILLRX");
        // getCodeValueModels, getRsRxLinkingInsertDTOs
        when(query.fetch()).thenReturn(getCodeValue(), dto);
        // sysUserNum
        when(query.fetchFirst()).thenReturn(101L);
        rsRxLinkingInsertService.insertRsRxLinking(new BigInteger("1"), 1010L, "I", null, null);
        verify(query, times(2)).fetch();
    }

    @Test
    public void test_insertRsRxLinking_expired() {
        when(epostrxQueryFactory.select(any(Expression.class))).thenReturn(query);
        List<RsRxLinkingInsertDTO> dto = getRsRxLinkingInsertDTOList();
        dto.get(0).setRxExpirationDate(Timestamp.from(Instant.now().minusSeconds(100)));
        // getCodeValueModels, getRsRxLinkingInsertDTOs
        when(query.fetch()).thenReturn(getCodeValue(), dto);
        // sysUserNum
        when(query.fetchFirst()).thenReturn(101L);
        rsRxLinkingInsertService.insertRsRxLinking(new BigInteger("1"), 1010L, "I", null, null);
        verify(query, times(2)).fetch();
    }

    @Test
    public void test_insertRsRxLinking_hasMultiLink() {
        when(epostrxQueryFactory.select(any(Expression.class))).thenReturn(query);
        List<RsRxLinkingInsertDTO> dto = getRsRxLinkingInsertDTOList();
        dto.get(0).setRxExpirationDate(Timestamp.from(Instant.now().plusSeconds(100)));
        dto.get(0).setHasMultiLink(1);
        // getCodeValueModels, getRsRxLinkingInsertDTOs
        when(query.fetch()).thenReturn(getCodeValue(), dto);
        // sysUserNum
        when(query.fetchFirst()).thenReturn(101L);
        rsRxLinkingInsertService.insertRsRxLinking(new BigInteger("1"), 1010L, "I", null, null);
        verify(query, times(2)).fetch();
    }

    @Test
    public void test_insertRsRxLinking_noPatientNum() {
        when(epostrxQueryFactory.select(any(Expression.class))).thenReturn(query);
        List<RsRxLinkingInsertDTO> dto = getRsRxLinkingInsertDTOList();
        dto.get(0).setPatientNum(null);
        // getCodeValueModels, getRsRxLinkingInsertDTOs
        when(query.fetch()).thenReturn(getCodeValue(), dto);
        // sysUserNum
        when(query.fetchFirst()).thenReturn(101L);
        rsRxLinkingInsertService.insertRsRxLinking(new BigInteger("1"), 1010L, "I", null, null);
        verify(query, times(2)).fetch();
    }

    @Test
    public void test_insertRsRxLinking_noRxNumber() {
        when(epostrxQueryFactory.select(any(Expression.class))).thenReturn(query);
        List<RsRxLinkingInsertDTO> dto = getRsRxLinkingInsertDTOList();
        dto.get(0).setRxNumber(null);
        // getCodeValueModels, getRsRxLinkingInsertDTOs
        when(query.fetch()).thenReturn(getCodeValue(), dto);
        // sysUserNum
        when(query.fetchFirst()).thenReturn(101L);
        rsRxLinkingInsertService.insertRsRxLinking(new BigInteger("1"), 1010L, "I", null, null);
        verify(query, times(2)).fetch();
    }

    @Test
    public void test_insertRsRxLinking_noProdGenericRefNum() {
        when(epostrxQueryFactory.select(any(Expression.class))).thenReturn(query);
        List<RsRxLinkingInsertDTO> dto = getRsRxLinkingInsertDTOList();
        dto.get(0).setProdGenericRefNum(null);
        // getCodeValueModels, getRsRxLinkingInsertDTOs
        when(query.fetch()).thenReturn(getCodeValue(), dto);
        // sysUserNum
        when(query.fetchFirst()).thenReturn(101L);
        rsRxLinkingInsertService.insertRsRxLinking(new BigInteger("1"), 1010L, "I", null, null);
        verify(query, times(2)).fetch();
    }

    @Test
    public void test_insertRsRxLinking_noDispensedDrugSig() {
        when(epostrxQueryFactory.select(any(Expression.class))).thenReturn(query);
        List<RsRxLinkingInsertDTO> dto = getRsRxLinkingInsertDTOList();
        dto.get(0).setDispensedDrugSig(null);
        // getCodeValueModels, getRsRxLinkingInsertDTOs
        when(query.fetch()).thenReturn(getCodeValue(), dto);
        // sysUserNum
        when(query.fetchFirst()).thenReturn(101L);
        rsRxLinkingInsertService.insertRsRxLinking(new BigInteger("1"), 1010L, "I", null, null);
        verify(query, times(2)).fetch();
    }

    @Test
    public void test_insertRsRxLinking_noSigGroup() {
        when(epostrxQueryFactory.select(any(Expression.class))).thenReturn(query);
        List<RsRxLinkingInsertDTO> dto = getRsRxLinkingInsertDTOList();
        dto.get(0).setSigGroup(null);
        // getCodeValueModels, getRsRxLinkingInsertDTOs
        when(query.fetch()).thenReturn(getCodeValue(), dto);
        // sysUserNum
        when(query.fetchFirst()).thenReturn(101L);
        rsRxLinkingInsertService.insertRsRxLinking(new BigInteger("1"), 1010L, "I", null, null);
        verify(query, times(2)).fetch();
    }

    @Test
    public void test_insertRsRxLinking_noDispensedDrugDaw() {
        when(epostrxQueryFactory.select(any(Expression.class))).thenReturn(query);
        List<RsRxLinkingInsertDTO> dto = getRsRxLinkingInsertDTOList();
        dto.get(0).setDispensedDrugDaw(null);
        // getCodeValueModels, getRsRxLinkingInsertDTOs
        when(query.fetch()).thenReturn(getCodeValue(), dto);
        // sysUserNum
        when(query.fetchFirst()).thenReturn(101L);
        rsRxLinkingInsertService.insertRsRxLinking(new BigInteger("1"), 1010L, "I", null, null);
        verify(query, times(2)).fetch();
    }

    @Test
    public void test_insertRsRxLinking_noRsRxLinkingDTO() {
        when(epostrxQueryFactory.select(any(Expression.class))).thenReturn(query);
        // getCodeValueModels, getRsRxLinkingInsertDTOs, getRsRxLinkingInsertDTO
        when(query.fetch()).thenReturn(getCodeValue(), getRsRxLinkingInsertDTOList(), new ArrayList());
        // sysUserNum
        when(query.fetchFirst()).thenReturn(101L);
        rsRxLinkingInsertService.insertRsRxLinking(new BigInteger("1"), 1010L, "I", null, null);
        verify(query, times(3)).fetch();
    }

    @Test
    public void test_insertRsRxLinking_noRxNumberParent() {
        when(epostrxQueryFactory.select(any(Expression.class))).thenReturn(query);
        List<RsRxLinkingInsertDTO> dto = getRsRxLinkingInsertDTO();
        dto.get(1).setRxNumber(null);
        // getCodeValueModels, getRsRxLinkingInsertDTOs, getRsRxLinkingInsertDTO
        when(query.fetch()).thenReturn(getCodeValue(), getRsRxLinkingInsertDTOList(), dto);
        // sysUserNum
        when(query.fetchFirst()).thenReturn(101L);
        rsRxLinkingInsertService.insertRsRxLinking(new BigInteger("1"), 1010L, "I", null, null);
        verify(query, times(3)).fetch();
    }

    @Test
    public void test_insertRsRxLinking_meetLimit() {
        when(epostrxQueryFactory.select(any(Expression.class))).thenReturn(query);
        when(functionsService.getRsRxLinkingParentChild("2", "Y"))
                .thenReturn(getRsRxLinkingParentChild());
        List<RsRxLinkingInsertDTO> dto = getRsRxLinkingInsertDTO();
        // getCodeValueModels, getRsRxLinkingInsertDTOs, getRsRxLinkingInsertDTO
        when(query.fetch()).thenReturn(getCodeValue(), getRsRxLinkingInsertDTOList(), dto);
        // sysUserNum, getRxMultiLink, getRxMultiLink
        when(query.fetchFirst()).thenReturn(101L, new RxMultiLink(), null);
        when(epostrxQueryFactory.delete(any(RelationalPath.class))).thenReturn(deleteClause);
        when(deleteClause.execute()).thenReturn(0L);
        when(epostrxQueryFactory.insert(any(RelationalPath.class))).thenReturn(insertClause);
        when(insertClause.execute()).thenReturn(0L);
        rsRxLinkingInsertService.insertRsRxLinking(new BigInteger("1"), 1010L, "I", null, null);
        verify(query, times(3)).fetch();
        verify(deleteClause).execute();
        verify(insertClause, times(3)).execute();
    }

    @Test
    public void test_insertRsRxLinking_noMeetLimit() {
        when(epostrxQueryFactory.select(any(Expression.class))).thenReturn(query);
        when(functionsService.getRsRxLinkingParentChild("2", "Y")).thenReturn(new ArrayList<>());
        List<RsRxLinkingInsertDTO> dto = getRsRxLinkingInsertDTO();
        // getCodeValueModels, getRsRxLinkingInsertDTOs, getRsRxLinkingInsertDTO
        when(query.fetch()).thenReturn(getCodeValue(), getRsRxLinkingInsertDTOList(), dto);
        // sysUserNum, getRxMultiLink
        when(query.fetchFirst()).thenReturn(101L, new RxMultiLink());
        rsRxLinkingInsertService.insertRsRxLinking(new BigInteger("1"), 1010L, "I", null, null);
        verify(query, times(3)).fetch();
    }

    @Test
    public void test_insertRsRxLinking_deleteParent() {
        when(epostrxQueryFactory.select(any(Expression.class))).thenReturn(query);
        // getCodeValueModels, getRsRxLinkingInsertDTOs, getRsRxLinkingInsertDTO
        when(query.fetch()).thenReturn(getCodeValue(), getRsRxLinkingInsertDTOList());
        // sysUserNum, getRxMultiLink
        RxMultiLink rxMultiLink = new RxMultiLink();
        rxMultiLink.setRxLinkSeq(0L);
        when(query.fetchFirst()).thenReturn(101L, rxMultiLink);
        when(epostrxQueryFactory.update(any(RelationalPath.class))).thenReturn(updateClause);
        when(updateClause.execute()).thenReturn(0L);
        when(epostrxQueryFactory.insert(any(RelationalPath.class))).thenReturn(insertClause);
        when(insertClause.execute()).thenReturn(0L);
        rsRxLinkingInsertService.insertRsRxLinking(new BigInteger("1"), 1010L, "D", null, "P");
        verify(query, times(2)).fetch();
        verify(updateClause).execute();
        verify(insertClause).execute();
    }

    @Test
    public void test_insertRsRxLinking_deleteNoParent() {
        when(epostrxQueryFactory.select(any(Expression.class))).thenReturn(query);
        // getCodeValueModels, getRsRxLinkingInsertDTOs, getRsRxLinkingInsertDTO
        when(query.fetch()).thenReturn(getCodeValue(), getRsRxLinkingInsertDTOList());
        // sysUserNum, getRxMultiLink
        when(query.fetchFirst()).thenReturn(101L, null);
        when(epostrxQueryFactory.insert(any(RelationalPath.class))).thenReturn(insertClause);
        when(insertClause.execute()).thenReturn(0L);
        rsRxLinkingInsertService.insertRsRxLinking(new BigInteger("1"), 1010L, "D", null, "P");
        verify(query, times(2)).fetch();
        verify(insertClause).execute();
    }

    @Test
    public void test_insertRsRxLinking_deleteChild() {
        when(epostrxQueryFactory.select(any(Expression.class))).thenReturn(query);
        // getCodeValueModels, getRsRxLinkingInsertDTOs, getRsRxLinkingInsertDTO
        when(query.fetch()).thenReturn(getCodeValue(), getRsRxLinkingInsertDTOList());
        // sysUserNum, getRxMultiLink
        RxMultiLink rxMultiLink = new RxMultiLink();
        rxMultiLink.setRxLinkSeq(0L);
        rxMultiLink.setParentEScriptMsgAttributeSeq(new BigInteger("0"));
        when(query.fetchFirst()).thenReturn(101L, rxMultiLink);
        when(epostrxQueryFactory.update(any(RelationalPath.class))).thenReturn(updateClause);
        when(updateClause.execute()).thenReturn(0L);
        when(epostrxQueryFactory.insert(any(RelationalPath.class))).thenReturn(insertClause);
        when(insertClause.execute()).thenReturn(0L);
        rsRxLinkingInsertService.insertRsRxLinking(new BigInteger("1"), 1010L, "D", null, "C");
        verify(query, times(2)).fetch();
        verify(updateClause).execute();
        verify(insertClause).execute();
    }

    @Test
    public void test_insertRsRxLinking_deleteNoChild() {
        when(epostrxQueryFactory.select(any(Expression.class))).thenReturn(query);
        // getCodeValueModels, getRsRxLinkingInsertDTOs, getRsRxLinkingInsertDTO
        when(query.fetch()).thenReturn(getCodeValue(), new ArrayList());
        // sysUserNum, getRxMultiLink
        when(query.fetchFirst()).thenReturn(101L, null);
        when(epostrxQueryFactory.insert(any(RelationalPath.class))).thenReturn(insertClause);
        when(insertClause.execute()).thenReturn(0L);
        rsRxLinkingInsertService.insertRsRxLinking(new BigInteger("1"), 1010L, "D", null, "C");
        verify(query, times(2)).fetch();
        verify(insertClause).execute();
    }

    private List<CCodeValue> getCodeValue() {
        CCodeValue cn1 = new CCodeValue();
        cn1.setCodeValueKeyword("RX_LINKING_OTHER");
        cn1.setCodeValueId(0);

        CCodeValue cn2 = new CCodeValue();
        cn2.setCodeValueKeyword("RX_LINKING_LIMIT");
        cn2.setCodeValueId(1);

        CCodeValue cn3 = new CCodeValue();
        cn3.setCodeValueKeyword("RX_LINK_SWITCH");
        cn3.setStatusFlag("B");
        cn3.setCodeValueId(2);

        CCodeValue c1 = new CCodeValue();
        c1.setCodeValueKeyword("RX_LINKING_LIMIT");
        c1.setCodeValueDesc("8");
        c1.setCodeValueId(3);

        CCodeValue c2 = new CCodeValue();
        c2.setCodeValueKeyword("RX_LINK_SWITCH");
        c2.setStatusFlag("A");
        c2.setCodeValueId(4);
        return Arrays.asList(cn1, cn2, cn3, c1, c2);
    }

    private List<RsRxLinkingInsertDTO> getRsRxLinkingInsertDTOList() {
        RsRxLinkingInsertDTO dto = new RsRxLinkingInsertDTO();
        dto.setEdiTransactionCode("FILLRX");
        dto.setPatientNum(new BigInteger("1"));
        dto.setRxNumber("1");
        dto.setProdGenericRefNum("1");
        dto.setDispensedDrugSig("1");
        dto.setSigGroup(1);
        dto.setDispensedDrugDaw("1");
        return Arrays.asList(dto);
    }

    private List<RsRxLinkingInsertDTO> getRsRxLinkingInsertDTO() {
        RsRxLinkingInsertDTO toFilter = new RsRxLinkingInsertDTO();
        toFilter.setRxLinkDccoGPIListGpi("gpi");
        RsRxLinkingInsertDTO dto = new RsRxLinkingInsertDTO();
        dto.setRxNumber("2");
        dto.setEScriptMsgAttributeSeq(new BigInteger("2"));
        return Arrays.asList(toFilter, dto);
    }

    private List<RsMultiLinkDTO> getRsRxLinkingParentChild() {
        List<RsMultiLinkDTO> result = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            RsMultiLinkDTO dto = new RsMultiLinkDTO();
            dto.setRxLinkSeq(i + 1L);
            result.add(dto);
        }
        return result;
    }
}
