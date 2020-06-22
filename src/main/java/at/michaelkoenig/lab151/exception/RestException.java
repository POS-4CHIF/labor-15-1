package at.michaelkoenig.lab151.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Michael König
 */
public class RestException extends RuntimeException {
    private final HttpStatus status;

    public RestException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }


}
