package bookingapplication.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
public class CustomerDto {
    private Long customerId;
    private String name;
}
