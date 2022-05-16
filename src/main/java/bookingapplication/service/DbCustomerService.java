package bookingapplication.service;

import bookingapplication.domain.Customer;
import bookingapplication.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DbCustomerService {
    private final CustomerRepository customerRepository;

    public Customer findCustomer(Long customerId) throws Exception {
        return customerRepository.findById(customerId).orElseThrow(Exception::new);
    }
}
