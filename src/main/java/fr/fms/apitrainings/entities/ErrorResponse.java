package fr.fms.apitrainings.entities;

import lombok.Data;

import java.util.List;

/**
 * Represents an error response.
 */
@Data
public class ErrorResponse {

    /**
     * The error message.
     */
    private String message;

    /**
     * The details of the error.
     */
    private List<String> details;

    /**
     * Constructs a new ErrorResponse with the specified message and details.
     *
     * @param message the error message.
     * @param details the details of the error.
     */
    public ErrorResponse(String message, List<String> details) {
        super();
        this.message = message;
        this.details = details;
    }
}
