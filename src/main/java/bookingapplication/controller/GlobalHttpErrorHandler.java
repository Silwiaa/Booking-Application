package bookingapplication.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalHttpErrorHandler extends ResponseEntityExceptionHandler {
    //@ExceptionHandler(BaggageNotFoundException.class)
    //public ResponseEntity<Object> handleBaggageNotFoundException(BaggageNotFoundException baggageNotFoundException) {
    //    return new ResponseEntity<>("Baggage with given id doesn't exist or can't be found in repository", HttpStatus.NOT_FOUND);
    //}
}
