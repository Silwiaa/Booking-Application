package bookingapplication.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity(name ="FACILITIES")
public class Facility {
    @Id
    @GeneratedValue
    @Column(name = "FACILITY_ID")
    private Long facilityId;

    @NotNull
    @Column(name = "NAME")
    private String name;

    @NotNull
    @Column(name = "PRICE")
    private BigDecimal price;

    @NotNull
    @Column(name = "AREA")
    private double area;

    @Column(name = "DESCRIPTION")
    private String description;

    @ManyToOne
    @JoinColumn(name = "OWNER_ID")
    private Owner owner;
}
