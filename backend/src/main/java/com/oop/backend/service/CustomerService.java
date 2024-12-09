package com.oop.backend.service;
import com.oop.backend.entity.Customer;
import com.oop.backend.repo.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CustomerService {
    private final CustomerRepository customerRepository;
    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer customerSave(Customer customer){
        return customerRepository.save(customer);
    }

}
