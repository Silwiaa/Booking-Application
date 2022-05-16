package bookingapplication.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity(name ="BOOKINGS")
public class Booking {
    @Id
    @GeneratedValue
    @Column(name = "BOOKING_ID")
    private Long bookingId;

    @NotNull
    @Column(name = "FROM_DATE")
    private LocalDate fromDate;

    @NotNull
    @Column(name = "TO_DATE")
    private LocalDate toDate;

    @NotNull
    @Column(name = "PRICE")
    private BigDecimal price;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "FACILITY_ID")
    private Facility facility;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMER_ID")
    private Customer customer;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "OWNER_ID")
    private Owner owner;
}
