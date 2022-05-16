package bookingapplication.service;

import bookingapplication.domain.*;
import bookingapplication.repository.BookingRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DbBookingServiceTestSuite {
    @InjectMocks
    private DbBookingService dbBookingService;

    @Mock
    private BookingRepository bookingRepository;

    @Test
    void shouldSaveBooking() {
        //Given
        Facility facility = Facility.builder()
                .facilityId(7L)
                .name("Facility 7")
                .price(new BigDecimal(150))
                .area(35)
                .description("Quisque non urna feugiat, lobortis arcu sed, maximus nunc")
                .build();

        Customer customer = Customer.builder()
                .customerId(4L)
                .name("Jane Doe")
                .build();

        Owner owner = Owner.builder()
                .ownerId(4L)
                .name("Mike Doe")
                .build();

        Booking booking = Booking.builder()
                .bookingId(7L)
                .fromDate(LocalDate.of(2022, 6, 12))
                .toDate(LocalDate.of(2022,6, 15))
                .price(BigDecimal.ZERO)
                .facility(facility)
                .customer(customer)
                .owner(owner)
                .build();

        when(bookingRepository.save(booking)).thenReturn(booking);

        //When
        Booking savedBooking = dbBookingService.saveBooking(booking);

        //Then
        assertEquals(savedBooking, booking);
    }
}
