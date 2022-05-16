package bookingapplication.validator;

import bookingapplication.domain.Booking;
import bookingapplication.service.DbBookingService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class BookingValidator {
    private static final Logger LOGGER = LoggerFactory.getLogger(BookingValidator.class);
    private final DbBookingService dbBookingService;

    public Booking validateBooking(Booking booking) throws Exception {
        validateBookingDates(booking.getFromDate(), booking.getToDate());
        List<Booking> bookingForFacilityList = dbBookingService.findBookings(booking.getFacility().getFacilityId());
        List<Boolean> overlappingList = bookingForFacilityList.stream()
                .filter(b -> !b.getBookingId().equals(booking.getBookingId()))
                .map(b -> bookingOverlaps(b, booking.getFromDate(), booking.getToDate()))
                .filter(r -> r.equals(true)).collect(Collectors.toList());
        if(overlappingList.size() == 0) {
            return booking;
        } else {
            LOGGER.info("Booking for >>" + booking.getFacility().getName() + "<< not available in given dates");
            throw new Exception();
        }
    }

    public void validateBookingDates(LocalDate dateFrom, LocalDate dateTo) throws Exception {
        if (LocalDate.now().isAfter(dateFrom)) {
            LOGGER.error("Can not make booking in the past");
            throw new Exception();
        }
        if(dateTo.isBefore(dateFrom)) {
            LOGGER.error("To date can not be before from date");
            throw new Exception();
        }
        if(dateTo.isEqual(dateFrom)) {
            LOGGER.error("To date can not be equal to from date");
            throw new Exception();
        }
    }

    public boolean bookingOverlaps(Booking booking, LocalDate fromDate, LocalDate toDate) {
        if (    fromDate.isBefore(booking.getFromDate()) && toDate.isAfter(booking.getToDate()) ||
                fromDate.isAfter(booking.getFromDate()) && fromDate.isBefore(booking.getToDate()) ||
                toDate.isAfter(booking.getFromDate()) && toDate.isBefore(booking.getToDate()) ||
                fromDate.isEqual(booking.getFromDate()) ||
                fromDate.isEqual(booking.getToDate()) ||
                toDate.isEqual(booking.getFromDate()) ||
                toDate.isEqual(booking.getToDate())
        ) {
            return true;
        } else {
            return false;
        }
    }
}
