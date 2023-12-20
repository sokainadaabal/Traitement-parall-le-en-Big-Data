package org.sid.customercommandside.controllers;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.sid.coreapi.commands.CreateCustomerCommand;
import org.sid.coreapi.dtos.CustomerRequestDTO;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;


@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/customers/commands")
public class CustomerCommandController {

    private CommandGateway commandGateway;
    private EventStore eventStore;
    @PostMapping(path = "/create")
    public CompletableFuture<String> newCustomer(@RequestBody CustomerRequestDTO requestDTO){
        return commandGateway.send(new CreateCustomerCommand(
                UUID.randomUUID().toString(),
                requestDTO.getName(),
                requestDTO.getEmail()
        ));
    }

    @GetMapping("/eventStore/{customerId}")
    public Stream eventStore(@PathVariable String customerId){
        return eventStore.readEvents(customerId).asStream();
    }

}
