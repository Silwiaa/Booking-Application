package bookingapplication.service;

import bookingapplication.domain.Customer;
import bookingapplication.exception.CustomerNotFoundByNameException;
import bookingapplication.exception.CustomerNotFoundException;
import bookingapplication.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DbCustomerService {
    private final CustomerRepository customerRepository;

    public Customer findCustomer(Long customerId) throws CustomerNotFoundException {
        return customerRepository.findById(customerId).orElseThrow(CustomerNotFoundException::new);
    }

    public Customer findCustomerByName(String customerName) throws CustomerNotFoundByNameException {
        return customerRepository.findCustomerByName(customerName).orElseThrow(CustomerNotFoundByNameException::new);
    }
}
