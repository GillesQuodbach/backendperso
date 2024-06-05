package fr.fms.apitrainings.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception to be thrown when a requested record is not found.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class RecordNotFoundException extends RuntimeException {

    /**
     * Constructs a new RecordNotFoundException with the specified detail message.
     *
     * @param message the detail message.
     */
    public RecordNotFoundException(String message) {
        super(message);
    }
}
