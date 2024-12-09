package com.oop.backend.controller;

import com.oop.backend.entity.Customer;
import com.oop.backend.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
@CrossOrigin

public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping("saveCustomer")
    public Customer saveCustomer(@RequestBody Customer customer){
        return customerService.customerSave(customer);
    }
}
