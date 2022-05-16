package bookingapplication.validator;

import bookingapplication.domain.Booking;
import bookingapplication.exception.BookingInThePastException;
import bookingapplication.exception.FromDateEqualsToDateException;
import bookingapplication.exception.OverlapsWithAnotherBookingException;
import bookingapplication.exception.ToDateBeforeFromDateException;
import bookingapplication.service.DbBookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BookingValidator {
    private final DbBookingService dbBookingService;

    public Booking validateBooking(Booking booking) throws Exception {
        validateBookingDates(booking.getFromDate(), booking.getToDate());
        List<Booking> bookingForFacilityList = dbBookingService.findBookings(booking.getFacility().getFacilityId());
        bookingForFacilityList.stream()
                .filter(b -> !b.getBookingId().equals(booking.getBookingId()))
                .forEach(o -> {
                    try {
                        bookingOverlaps(o, booking.getFromDate(), booking.getToDate());
                    } catch (OverlapsWithAnotherBookingException e) {
                        e.printStackTrace();
                    }
                });

        return booking;
    }

    public void validateBookingDates(LocalDate dateFrom, LocalDate dateTo) throws Exception {
        if(LocalDate.now().isAfter(dateFrom)) throw new BookingInThePastException();
        if(dateTo.isBefore(dateFrom)) throw new ToDateBeforeFromDateException();
        if(dateTo.isEqual(dateFrom)) throw new FromDateEqualsToDateException();
    }

    public void bookingOverlaps(Booking booking, LocalDate fromDate, LocalDate toDate) throws OverlapsWithAnotherBookingException {
       if (
               fromDate.isBefore(booking.getFromDate()) && toDate.isAfter(booking.getToDate()) ||
                       fromDate.isAfter(booking.getFromDate()) && fromDate.isBefore(booking.getToDate()) ||
                       toDate.isAfter(booking.getFromDate()) && toDate.isBefore(booking.getToDate()) ||
                       fromDate.isEqual(booking.getFromDate()) ||
                       fromDate.isEqual(booking.getToDate()) ||
                       toDate.isEqual(booking.getFromDate()) ||
                       toDate.isEqual(booking.getToDate())
        ) {
            throw new OverlapsWithAnotherBookingException();
        }
    }
}
