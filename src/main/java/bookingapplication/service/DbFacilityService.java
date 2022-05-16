package bookingapplication.service;

import bookingapplication.domain.Facility;
import bookingapplication.repository.FacilityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DbFacilityService {
    private final FacilityRepository facilityRepository;

    public Facility findFacilityById(Long facilityId) throws Exception {
        return facilityRepository.findById(facilityId).orElseThrow(Exception::new);
    }
}
