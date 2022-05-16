package bookingapplication.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class CustomerDto {
    private Long customerId;
    private String name;
}
