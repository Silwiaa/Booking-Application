package bookingapplication.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

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
