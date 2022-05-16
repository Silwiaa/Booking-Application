package bookingapplication.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity(name ="OWNERS")
public class Owner {
    @Id
    @GeneratedValue
    @Column(name = "OWNER_ID")
    private Long ownerId;

    @NotNull
    @Column(name = "NAME")
    private String name;

    @OneToMany(
            targetEntity = Booking.class,
            mappedBy = "owner",
            cascade = CascadeType.ALL
    )
    private List<Booking> bookingList;

    @OneToMany(
            targetEntity = Facility.class,
            mappedBy = "owner",
            cascade = CascadeType.ALL
    )
    private List<Facility> facilitiesList;
}
