package bookingapplication.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Builder
@AllArgsConstructor
public class FacilityDto {
    private Long facilityId;
    private String name;
    private BigDecimal price;
    private double area;
    private String description;
    private Owner ownerId;
}
