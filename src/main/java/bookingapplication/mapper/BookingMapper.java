package bookingapplication.mapper;


import bookingapplication.domain.Booking;
import bookingapplication.domain.BookingDto;
import bookingapplication.parser.DateParser;
import bookingapplication.service.DbCustomerService;
import bookingapplication.service.DbFacilityService;
import bookingapplication.service.DbOwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingMapper {
    private final DbFacilityService dbFacilityService;
    private final DbCustomerService dbCustomerService;
    private final DbOwnerService dbOwnerService;
    private final DateParser dateParser;

    public Booking mapToBooking(BookingDto bookingDto) throws Exception {
        return Booking.builder()
                .bookingId(bookingDto.getBookingId())
                .fromDate(dateParser.parseDate(bookingDto.getFromDate()))
                .toDate(dateParser.parseDate(bookingDto.getToDate()))
                .price(new BigDecimal(bookingDto.getPrice()))
                .facility(dbFacilityService.findFacilityById(bookingDto.getFacilityId()))
                .customer(dbCustomerService.findCustomer(bookingDto.getCustomerId()))
                .owner(dbOwnerService.findOwner(bookingDto.getOwnerId()))
                .build();
    }

    public BookingDto mapToBookingDto(Booking booking) {
        return BookingDto.builder()
                .bookingId(booking.getBookingId())
                .fromDate(booking.getFromDate().toString())
                .toDate(booking.getToDate().toString())
                .price(booking.getPrice().doubleValue())
                .facilityId(booking.getFacility().getFacilityId())
                .customerId(booking.getCustomer().getCustomerId())
                .ownerId(booking.getOwner().getOwnerId())
                .build();
    }

    public List<BookingDto> mapToBookingDtoList(List<Booking> bookingList) {
        return bookingList.stream()
                .map(this::mapToBookingDto)
                .collect(Collectors.toList());
    }
}
