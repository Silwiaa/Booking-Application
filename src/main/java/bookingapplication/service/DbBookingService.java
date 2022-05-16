package bookingapplication.service;

import bookingapplication.domain.Booking;
import bookingapplication.exception.BookingNotFoundException;
import bookingapplication.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DbBookingService {
    private final BookingRepository bookingRepository;

    public Booking saveBooking(Booking booking) {
        booking.setPrice(countPrice(booking.getFromDate(),booking.getToDate(), booking.getFacility().getPrice()));
        return bookingRepository.save(booking);
    }

    public Booking findBookingById(Long bookingId) throws BookingNotFoundException {
        return bookingRepository.findById(bookingId).orElseThrow(BookingNotFoundException::new);
    }

    public Booking updateBooking(Long bookingId, LocalDate fromDate, LocalDate toDate) throws Exception {
        Booking booking = findBookingById(bookingId);
        booking.setFromDate(fromDate);
        booking.setToDate(toDate);
        saveBooking(booking);
        return booking;
    }

    public List<Booking> findBookingsByCustomerName(String customerName) {
        return bookingRepository.findBookingsByCustomerName(customerName);
    }

    public List<Booking> findBookings(Long facilityId) {
        return bookingRepository.findBookingsByFacility_FacilityId(facilityId);
    }

    private BigDecimal countPrice(LocalDate dateFrom, LocalDate dateTo, BigDecimal price) {
        return price.multiply(new BigDecimal(ChronoUnit.DAYS.between(dateFrom, dateTo)));
    }
}
