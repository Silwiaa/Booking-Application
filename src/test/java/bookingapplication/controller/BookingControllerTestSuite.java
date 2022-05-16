package bookingapplication.controller;

import bookingapplication.domain.*;
import bookingapplication.mapper.BookingMapper;
import bookingapplication.parser.DateParser;
import bookingapplication.service.DbBookingService;
import bookingapplication.validator.BookingValidator;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(BookingController.class)
public class BookingControllerTestSuite {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DbBookingService dbBookingService;

    @MockBean
    private BookingMapper bookingMapper;

    @MockBean
    private BookingValidator bookingValidator;

    @MockBean
    private DateParser dateParser;

    @Test
    void shouldCreateBooking() throws Exception {
        //Given
        BookingDto bookingDto = BookingDto.builder()
                .bookingId(8L)
                .fromDate("2022-07-01")
                .toDate("2022-07-08")
                .price(1750)
                .facilityId(1L)
                .ownerId(1L)
                .customerId(1L)
                .build();

        Customer customer = Customer.builder()
                .customerId(1L)
                .build();

        Owner owner = Owner.builder()
                .ownerId(1L)
                .build();

        Facility facility = Facility.builder()
                .facilityId(1L)
                .build();

        Booking booking = Booking.builder()
                .bookingId(bookingDto.getBookingId())
                .fromDate(LocalDate.of(2022, 7, 1))
                .toDate(LocalDate.of(2022, 7, 8))
                .price(new BigDecimal(bookingDto.getPrice()))
                .facility(facility)
                .owner(owner)
                .customer(customer)
                .build();

        when(bookingValidator.validateBooking(booking, Method.POST.getResource())).thenReturn(booking);
        when(bookingMapper.mapToBooking(bookingDto)).thenReturn(booking);
        when(dbBookingService.saveBooking(booking)).thenReturn(booking);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(bookingDto);

        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/booking/createBooking")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
