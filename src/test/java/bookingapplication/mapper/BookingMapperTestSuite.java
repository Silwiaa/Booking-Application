package bookingapplication.mapper;

import bookingapplication.domain.*;
import bookingapplication.parser.DateParser;
import bookingapplication.service.DbCustomerService;
import bookingapplication.service.DbFacilityService;
import bookingapplication.service.DbOwnerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookingMapperTestSuite {
    @InjectMocks
    private BookingMapper bookingMapper;

    @Mock
    private DbFacilityService dbFacilityService;

    @Mock
    private DbCustomerService dbCustomerService;

    @Mock
    private DbOwnerService dbOwnerService;

    @Mock
    private DateParser dateParser;

    @Test
    void testMapToBooking() throws Exception {
        //Given
        Customer customer = Customer.builder()
                .customerId(1L)
                .build();

        Owner owner = Owner.builder()
                .ownerId(1L)
                .build();

        Facility facility = Facility.builder()
                .facilityId(1L)
                .build();

        BookingDto bookingDto = BookingDto.builder()
                .bookingId(8L)
                .fromDate("2022-06-01")
                .toDate("2022-06-08")
                .price(1750)
                .facilityId(facility.getFacilityId())
                .customerId(customer.getCustomerId())
                .ownerId(owner.getOwnerId())
                .build();

        when(dbFacilityService.findFacilityById(bookingDto.getFacilityId())).thenReturn(facility);
        when(dbCustomerService.findCustomer(bookingDto.getCustomerId())).thenReturn(customer);
        when(dbOwnerService.findOwner(bookingDto.getOwnerId())).thenReturn(owner);
        when(dateParser.parseDate(bookingDto.getFromDate())).thenReturn(LocalDate.of(2022, 6, 1));
        when(dateParser.parseDate(bookingDto.getToDate())).thenReturn(LocalDate.of(2022, 6, 8));

        //When
        Booking resultBooking = bookingMapper.mapToBooking(bookingDto);

        //Then
        assertEquals(bookingDto.getBookingId(), resultBooking.getBookingId());
        assertEquals(bookingDto.getFromDate(), resultBooking.getFromDate().toString());
        assertEquals(bookingDto.getToDate(), resultBooking.getToDate().toString());
        assertEquals(bookingDto.getPrice(), resultBooking.getPrice().doubleValue(), 0.01);
        assertEquals(bookingDto.getFacilityId(), resultBooking.getFacility().getFacilityId());
        assertEquals(bookingDto.getCustomerId(), resultBooking.getCustomer().getCustomerId());
        assertEquals(bookingDto.getOwnerId(), resultBooking.getOwner().getOwnerId());
    }
}
