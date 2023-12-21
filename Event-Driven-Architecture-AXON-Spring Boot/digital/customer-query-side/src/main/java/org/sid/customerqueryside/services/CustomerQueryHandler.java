package org.sid.customerqueryside.services;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.queryhandling.QueryHandler;
import org.sid.coreapi.query.GetAllCustmersQuery;
import org.sid.coreapi.query.GetCustmerByIdQuery;
import org.sid.customerqueryside.entities.Customer;
import org.sid.customerqueryside.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class CustomerQueryHandler {
    public CustomerRepository customerRepository;

    @QueryHandler
    public List<Customer> customers(GetAllCustmersQuery query){
        return customerRepository.findAll();
    }

    @QueryHandler
    public Customer customerById(GetCustmerByIdQuery query){
        return customerRepository.findById(query.getId()).orElseThrow(()->new RuntimeException("Customer not found"));
    }
}
