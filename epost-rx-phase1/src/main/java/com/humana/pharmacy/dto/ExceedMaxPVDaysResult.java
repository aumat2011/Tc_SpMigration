package com.humana.pharmacy.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * The result dto for checking order exceed max PV days.
 */
@Setter
@Getter
public class ExceedMaxPVDaysResult {
    /**
     * The rx numbers exceeding max PV days.
     */
    private List<String> rxNumbers;

    /**
     * Max PV days.
     */
    private Short maxPVDays;

    /**
     * Override queue number.
     */
    private Integer overrideQueue;

    /**
     * Override queue name.
     */
    private String overrideQname;

    /**
     * Get error messages representation.
     *
     * @return Error messages representation
     */
    public String toErrorMsgs() {

        StringBuilder sb = new StringBuilder();
        if (rxNumbers != null && !rxNumbers.isEmpty()) {
            int rxNumbersSize = rxNumbers.size();
            for (int i = 1; i <= rxNumbersSize; i++) {
                sb.append(" (").append(i).append(") Rx#").append(rxNumbers.get(i - 1))
                        .append(" Greater than ").append(maxPVDays).append(" days from written date ").append("\n");
            }
        }
        return sb.toString();
    }
}

