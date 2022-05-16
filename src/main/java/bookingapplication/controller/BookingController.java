package bookingapplication.controller;

import bookingapplication.domain.Booking;
import bookingapplication.domain.BookingDto;
import bookingapplication.mapper.BookingMapper;
import bookingapplication.parser.DateParser;
import bookingapplication.service.DbBookingService;
import bookingapplication.validator.BookingValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/v1/booking")
@RequiredArgsConstructor
public class BookingController {
    private final DbBookingService dbBookingService;
    private final BookingMapper bookingMapper;
    private final BookingValidator bookingValidator;
    private final DateParser dateParser;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, value = "/createBooking")
    public ResponseEntity<Void> createBooking(@RequestBody BookingDto bookingDto) throws Exception {
        Booking booking = bookingValidator.validateBooking(bookingMapper.mapToBooking(bookingDto));
        dbBookingService.saveBooking(booking);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/updateBooking")
    public ResponseEntity<BookingDto> updateBooking(
            @RequestParam Long bookingId, @RequestParam String dateFrom, @RequestParam String dateTo
    ) throws Exception {
        Booking booking = bookingValidator.validateBooking(dbBookingService.updateBooking(
                bookingId, dateParser.parseDate(dateFrom), dateParser.parseDate(dateTo)));
        dbBookingService.saveBooking(booking);
        return ResponseEntity.ok(bookingMapper.mapToBookingDto(booking));
    }

    @GetMapping("/getCustomerBookings")
    public ResponseEntity<List<BookingDto>> getCustomerBookings(@RequestParam String customerName) throws Exception {
        return ResponseEntity.ok(bookingMapper.mapToBookingDtoList(dbBookingService.findBookingsByCustomerName(customerName)));
    }

    @GetMapping("/getBookings")
    public ResponseEntity<List<BookingDto>> getBookings(@RequestParam Long facilityId) throws Exception {
        return ResponseEntity.ok(bookingMapper.mapToBookingDtoList(dbBookingService.findBookings(facilityId)));
    }
}
