package com.humana.pharmacy.common.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

/**
 * The base dto to operate with table e_script_msg_attributes.
 */
@Setter
@Getter
public class BaseEScriptDTO {
    /**
     * The escript msg attribute seq.
     */
    private BigInteger eScriptMsgAttributeSeq;

    public void seteScriptMsgAttributeSeq(BigInteger eScriptMsgAttributeSeq) {
        this.eScriptMsgAttributeSeq = eScriptMsgAttributeSeq;
    }

    public BigInteger geteScriptMsgAttributeSeq() {
        return eScriptMsgAttributeSeq;
    }

    public void setEScriptMsgAttributeSeq(BigInteger eScriptMsgAttributeSeq) {
        this.eScriptMsgAttributeSeq = eScriptMsgAttributeSeq;
    }

    public BigInteger getEScriptMsgAttributeSeq() {
        return eScriptMsgAttributeSeq;
    }
}

