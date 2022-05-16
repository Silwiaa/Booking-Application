package bookingapplication.controller;

import bookingapplication.exception.InvalidDateFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalHttpErrorHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(InvalidDateFormatException.class)
    public ResponseEntity<Object> handleInvalidDateFormatException(InvalidDateFormatException invalidDateFormatException) {
        return new ResponseEntity<>("Date needs accord to pattern yyyy-MM-dd", HttpStatus.NOT_ACCEPTABLE);
    }
}
