package bookingapplication.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@Getter
@AllArgsConstructor
public class BookingDto {
    private Long bookingId;
    private String fromDate;
    private String toDate;
    private double price;
    private Long facilityId;
    private Long customerId;
    private Long ownerId;
}
