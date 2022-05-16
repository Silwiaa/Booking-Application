package bookingapplication.repository;

import bookingapplication.domain.Booking;
import bookingapplication.domain.Customer;
import bookingapplication.domain.Facility;
import bookingapplication.domain.Owner;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookingRepositoryTestSuite {
    @Autowired
    private BookingRepository bookingRepository;

    @Test
    void testSaveBaggage() {
        //Given
        Facility facility = Facility.builder()
                .name("Facility 7")
                .price(new BigDecimal(150))
                .area(35)
                .description("Quisque non urna feugiat, lobortis arcu sed, maximus nunc")
                .build();

        Customer customer = Customer.builder()
                .name("Jane Doe")
                .build();

        Owner owner = Owner.builder()
                .name("Mike Doe")
                .build();

        Booking booking = Booking.builder()
                .fromDate(LocalDate.of(2022, 6, 12))
                .toDate(LocalDate.of(2022,6, 15))
                .price(new BigDecimal(350))
                .facility(facility)
                .customer(customer)
                .owner(owner)
                .build();

        //When
        bookingRepository.save(booking);
        Long id = booking.getBookingId();


        //Then
        assertTrue(bookingRepository.existsById(id));

        //CleanUp
        bookingRepository.deleteById(id);
    }
}
