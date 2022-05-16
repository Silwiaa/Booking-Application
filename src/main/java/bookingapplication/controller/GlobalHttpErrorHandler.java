package bookingapplication.controller;

import bookingapplication.exception.*;
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

    @ExceptionHandler(BookingNotFoundException.class)
    public ResponseEntity<Object> handleBookingNotFoundException(BookingNotFoundException bookingNotFoundException) {
        return new ResponseEntity<>("Booking with given id doesn't exist or can't be found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<Object> handleCustomerNotFoundException(CustomerNotFoundException customerNotFoundException) {
        return new ResponseEntity<>("Customer with given id doesn't exist or can't be found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CustomerNotFoundByNameException.class)
    public ResponseEntity<Object> handleCustomerNotFoundByNameException(CustomerNotFoundByNameException customerNotFoundByNameException) {
        return new ResponseEntity<>("Customer with given name doesn't exist or can't be found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FacilityNotFoundException.class)
    public ResponseEntity<Object> handleFacilityNotFoundException(FacilityNotFoundException facilityNotFoundException) {
        return new ResponseEntity<>("Facility with given id doesn't exist or can't be found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OwnerNotFoundException.class)
    public ResponseEntity<Object> handleOwnerNotFoundException(OwnerNotFoundException ownerNotFoundException) {
        return new ResponseEntity<>("Owner with given id doesn't exist or can't be found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OverlapsWithAnotherBookingException.class)
    public ResponseEntity<Object> handleOverlapsWithAnotherBookingException(OverlapsWithAnotherBookingException overlapsWithAnotherBookingException) {
        return new ResponseEntity<>("Booking not available during given dates - overlaps with another reservation", HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(BookingInThePastException.class)
    public ResponseEntity<Object> handleBookingInThePastException(BookingInThePastException bookingInThePastException) {
        return new ResponseEntity<>("Booking in the past not allowed", HttpStatus.NOT_ACCEPTABLE);
    }
    @ExceptionHandler(FromDateEqualsToDateException.class)
    public ResponseEntity<Object> handleFromDateEqualsToDateException(FromDateEqualsToDateException fromDateEqualsToDateException) {
        return new ResponseEntity<>("Make at least one day reservation", HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(ToDateBeforeFromDateException.class)
    public ResponseEntity<Object> handleToDateBeforeFromDateException(ToDateBeforeFromDateException toDateBeforeFromDateException) {
        return new ResponseEntity<>("To date before from date", HttpStatus.NOT_ACCEPTABLE);
    }
}
