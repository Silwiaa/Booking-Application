package bookingapplication.validator;

import bookingapplication.domain.Booking;
import bookingapplication.domain.Customer;
import bookingapplication.domain.Facility;
import bookingapplication.domain.Owner;
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

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
    void shouldFetchValidBooking() throws Exception {
        //Given
        when(dbBookingService.findBookings(booking.getFacility().getFacilityId())).thenReturn(new ArrayList<>());

        //When
        Booking resultBooking = bookingValidator.validateBooking(booking);

        //Then
        assertEquals(resultBooking, booking);
    }

    @Test
    void shouldReturnFalseIfBookingNoOverlaps() {
        //Given
        LocalDate fromDate = LocalDate.of(2022, 6, 16);
        LocalDate toDate = LocalDate.of(2022, 6, 17);

        //When
        boolean result = bookingValidator.bookingOverlaps(booking, fromDate, toDate);

        //Then
        assertFalse(result);
    }

    @Test
    void shouldReturnTrueIfBookingOverlaps() {
        //Given
        LocalDate fromDate = LocalDate.of(2022, 6, 10);
        LocalDate toDate = LocalDate.of(2022, 6, 12);

        LocalDate fromDate2 = LocalDate.of(2022, 6, 12);
        LocalDate toDate2 = LocalDate.of(2022, 6, 13);

        LocalDate fromDate3 = LocalDate.of(2022, 6, 13);
        LocalDate toDate3 = LocalDate.of(2022, 6, 17);

        LocalDate fromDate4 = LocalDate.of(2022, 6, 10);
        LocalDate toDate4 = LocalDate.of(2022, 6, 17);

        //When
        boolean result = bookingValidator.bookingOverlaps(booking, fromDate, toDate);
        boolean result2 = bookingValidator.bookingOverlaps(booking, fromDate2, toDate2);
        boolean result3 = bookingValidator.bookingOverlaps(booking, fromDate3, toDate3);
        boolean result4 = bookingValidator.bookingOverlaps(booking, fromDate4, toDate4);

        //Then
        assertTrue(result);
        assertTrue(result2);
        assertTrue(result3);
        assertTrue(result4);
    }
}