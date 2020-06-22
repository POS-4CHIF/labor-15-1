package at.michaelkoenig.lab151.exceptionhandler;

import at.michaelkoenig.lab151.exception.RestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author Michael König
 */
@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleAllOtherExceptions(Exception ex, WebRequest
            req) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RestException.class)
    public ResponseEntity<String> handleRestException(RestException ex, WebRequest
            req) {
        return new ResponseEntity<>(ex.getMessage(), ex.getStatus());
    }

}
