package com.OrderSystem.customer.controller;

import com.OrderSystem.customer.data.CustomerRepository;
import com.OrderSystem.customer.model.Customer;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/customer")
public class CustomerRestController {
    private CustomerRepository repository;

    public CustomerRestController(CustomerRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/customer/{customerID}")
    public List<Customer> getAllCustomerId(@PathVariable int customerID) {
        return repository.findByCustomerID(customerID);
    }


    @GetMapping
    public List<Customer> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{customerID}")
    public Customer getOne(@PathVariable int customerID) {
        try{
            return  repository.findById(customerID).get();
        }catch (NoSuchElementException e){
            return null;
        }
    }
    @PostMapping
    public Customer create(@RequestBody Customer customer){
        repository.save(customer);
        return customer ;
    }
    @PutMapping("/{customerID}")
    public Customer update(@PathVariable int customerID,
                          @RequestBody Customer customer) {
        Customer record = repository.findById(customerID).get();
        record.setCustomer_name(customer.getCustomer_name());
        record.setCustomer_surname(customer.getCustomer_surname());
        record.setCustomer_phone(customer.getCustomer_phone());
        record.setProvince(customer.getProvince());
        record.setPostal_id(customer.getPostal_id());
        repository.save(record);
        return record;
    }
    @DeleteMapping("/{customerID}")
    public Customer delete(@PathVariable int customerID) {
        Customer record = repository.findById(customerID).get();
        repository.deleteById(customerID);
        return record;
    }
}
