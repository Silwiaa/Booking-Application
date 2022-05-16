package bookingapplication.validator;

import bookingapplication.domain.*;
import bookingapplication.exception.BookingInThePastException;
import bookingapplication.exception.FromDateEqualsToDateException;
import bookingapplication.exception.OverlapsWithAnotherBookingException;
import bookingapplication.exception.ToDateBeforeFromDateException;
import bookingapplication.service.DbBookingService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookingValidatorTestSuite {
    @InjectMocks
    private BookingValidator bookingValidator;

    @Mock
    private DbBookingService dbBookingService;

    private static Booking booking;
    private static Facility facility;
    private static Customer customer;
    private static Owner owner;

    @BeforeAll
    public static void beforeAll() {
        facility = Facility.builder()
                .facilityId(7L)
                .name("Facility 7")
                .price(new BigDecimal(150))
                .area(35)
                .description("Quisque non urna feugiat, lobortis arcu sed, maximus nunc")
                .build();

        customer = Customer.builder()
                .customerId(4L)
                .name("Jane Doe")
                .build();

        owner = Owner.builder()
                .ownerId(4L)
                .name("Mike Doe")
                .build();
        booking = Booking.builder()
                .bookingId(7L)
                .fromDate(LocalDate.of(2022, 6, 12))
                .toDate(LocalDate.of(2022,6, 15))
                .price(BigDecimal.ZERO)
                .facility(facility)
                .customer(customer)
                .owner(owner)
                .build();
    }

    @Test
    void shouldFetchValidBookingWhenUpdateBooking() throws Exception {
        //Given
        List<Booking> bookingList = new ArrayList<>();
        bookingList.add(booking);
        when(dbBookingService.findBookings(booking.getFacility().getFacilityId())).thenReturn(bookingList);

        //When
        Booking resultBooking = bookingValidator.validateBooking(booking, Method.PUT.getResource());

        //Then
        assertEquals(resultBooking, booking);
    }

    @Test
    void shouldFetchValidBookingWhenCreateNewBooking() throws Exception {
        //Given
        List<Booking> bookingList = new ArrayList<>();
        Booking booking2 = Booking.builder()
                .bookingId(8L)
                .fromDate(LocalDate.of(2022, 6, 16))
                .toDate(LocalDate.of(2022,6, 18))
                .price(BigDecimal.ZERO)
                .facility(facility)
                .customer(customer)
                .owner(owner)
                .build();

        bookingList.add(booking);
        bookingList.add(booking2);

        when(dbBookingService.findBookings(booking.getFacility().getFacilityId())).thenReturn(bookingList);

        //When
        Booking resultBooking = bookingValidator.validateBooking(booking, Method.POST.getResource());

        //Then
        assertEquals(resultBooking, booking);
    }

    @Test
    void shouldThrowExceptionIfFromDateInThePast() {
        //Given
        LocalDate fromDate = LocalDate.of(2021, 6, 10);
        LocalDate toDate = LocalDate.of(2021, 6, 12);

        //When & Then
        assertThrows(BookingInThePastException.class, () -> bookingValidator.validateBookingDates(fromDate, toDate));
    }

    @Test
    void shouldThrowExceptionIfToDateBeforeFromDate() {
        //Given
        LocalDate fromDate = LocalDate.of(2022, 12, 10);
        LocalDate toDate = LocalDate.of(2022, 12, 9);

        //When & Then
        assertThrows(ToDateBeforeFromDateException.class, () -> bookingValidator.validateBookingDates(fromDate, toDate));
    }

    @Test
    void shouldThrowExceptionIfToDateEqualsFromDate() {
        //Given
        LocalDate fromDate = LocalDate.of(2022, 12, 10);
        LocalDate toDate = LocalDate.of(2022, 12, 10);

        //When & Then
        assertThrows(FromDateEqualsToDateException.class, () -> bookingValidator.validateBookingDates(fromDate, toDate));
    }

    @Test
    void shouldReturnFalseIfBookingOverlapsCase1() {
        //Given
        LocalDate fromDate = LocalDate.of(2022, 6, 10);
        LocalDate toDate = LocalDate.of(2022, 6, 12);

        //When & Then
        assertThrows(OverlapsWithAnotherBookingException.class, () -> bookingValidator.bookingOverlaps(booking, fromDate, toDate));
    }

    @Test
    void shouldReturnFalseIfBookingOverlapsCase2() {
        //Given
        LocalDate fromDate = LocalDate.of(2022, 6, 12);
        LocalDate toDate = LocalDate.of(2022, 6, 13);

        //When & Then
        assertThrows(OverlapsWithAnotherBookingException.class, () -> bookingValidator.bookingOverlaps(booking, fromDate, toDate));
    }

    @Test
    void shouldReturnFalseIfBookingOverlapsCase3() {
        //Given
        LocalDate fromDate = LocalDate.of(2022, 6, 13);
        LocalDate toDate = LocalDate.of(2022, 6, 17);

        //When & Then
        assertThrows(OverlapsWithAnotherBookingException.class, () -> bookingValidator.bookingOverlaps(booking, fromDate, toDate));
    }

    @Test
    void shouldReturnFalseIfBookingOverlapsCase4() {
        //Given
        LocalDate fromDate = LocalDate.of(2022, 6, 10);
        LocalDate toDate = LocalDate.of(2022, 6, 17);

        //When & Then
        assertThrows(OverlapsWithAnotherBookingException.class, () -> bookingValidator.bookingOverlaps(booking, fromDate, toDate));
    }
}