package com.humana.pharmacy.dto;

import com.humana.pharmacy.constants.OrderSanityError;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * The result dto for checking order sanity.
 */
@Setter
@Getter
public class OrderSanityResult {
    /**
     * List of sanity errors.
     */
    private List<OrderSanityError> errors;

    /**
     * List of expired Rxs. This field is present when having EXPIRED_RX error.
     */
    private List<String> expiredRxs;

    /**
     * List of insufficient Rxs. This field is present when having INSUFFICIENT_RX error.
     */
    private List<String> insufficientRxs;

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
        int errorsSize = errors.size();

        if (errors != null && !errors.isEmpty()) {
            for (int i = 1; i <= errorsSize; i++) {
                OrderSanityError error = errors.get(i - 1);
                sb.append(" (").append(i).append(") ").append(error.getError());
                if (error == OrderSanityError.EXPIRED_RX) {
                    sb.append(":" + String.join(",", expiredRxs));
                } else if (error == OrderSanityError.INSUFFICIENT_RX) {
                    sb.append(":" + String.join(",", insufficientRxs));
                }

                sb.append("\n");
            }
        }
        return sb.toString();
    }
}

