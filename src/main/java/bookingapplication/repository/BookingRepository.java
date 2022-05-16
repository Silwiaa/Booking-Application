package bookingapplication.repository;

import bookingapplication.domain.Booking;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends CrudRepository<Booking, Long> {
    List<Booking> findBookingsByCustomerName(String customerName);
    List<Booking> findBookingsByFacility_FacilityId(Long facilityId);
}