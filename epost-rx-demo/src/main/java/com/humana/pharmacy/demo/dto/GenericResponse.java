package com.humana.pharmacy.demo.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * The generic response (<b>NOTE: </b> Used for demo).
 */
@Getter
@Setter
public class GenericResponse {
    /**
     * The data.
     */
    private String data;
    /**
     * The message.
     */
    private String message;
    /**
     * The Status.
     */
    private String status;

    /**
     * Instantiates a new Generic response.
     *
     * @param data    the data
     * @param message the message
     * @param status  the status
     */
    public GenericResponse(String data, String message, String status) {
        this.data = data;
        this.message = message;
        this.status = status;
    }

    /**
     * Create "OK" response.
     *
     * @param data the data
     */
    public static GenericResponse OK(String data) {
        return new GenericResponse(data, "", "OK");
    }

    /**
     * Create "ERROR" response.
     *
     * @param message the message
     */
    public static GenericResponse ERROR(String message) {
        return new GenericResponse("", message, "ERROR");
    }
}
