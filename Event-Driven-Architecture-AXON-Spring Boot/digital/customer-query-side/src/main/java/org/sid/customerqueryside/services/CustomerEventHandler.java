package org.sid.customerqueryside.services;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.NoTypePermission;
import kotlin.jvm.internal.SerializedIr;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.gateway.EventGateway;
import org.sid.coreapi.commands.CreateCustomerCommand;
import org.sid.coreapi.events.CustomerCreatedEvent;
import org.sid.customerqueryside.entities.Customer;
import org.sid.customerqueryside.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class CustomerEventHandler {
    private CustomerRepository customerRepository;
    @EventHandler
    public void on(CustomerCreatedEvent event){
        log.info("************************");
        log.info("Handling CustomerCreatedEvent: {}", event);
        Customer customer = new Customer();
        customer.setId(event.getId());
        customer.setName(event.getName());
        customer.setEmail(event.getEmail());

        customerRepository.save(customer);
    }



}
