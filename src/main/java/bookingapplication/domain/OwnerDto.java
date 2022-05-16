package bookingapplication.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
public class OwnerDto {
    private Long ownerId;
    private String name;
}
