package bookingapplication.service;

import bookingapplication.domain.Owner;
import bookingapplication.repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DbOwnerService {
    private final OwnerRepository ownerRepository;

    public Owner findOwner(Long ownerId) throws Exception {
        return ownerRepository.findById(ownerId).orElseThrow(Exception::new);
    }
}
