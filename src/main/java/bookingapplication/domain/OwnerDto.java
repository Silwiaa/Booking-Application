package bookingapplication.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class OwnerDto {
    private Long ownerId;
    private String name;
}
