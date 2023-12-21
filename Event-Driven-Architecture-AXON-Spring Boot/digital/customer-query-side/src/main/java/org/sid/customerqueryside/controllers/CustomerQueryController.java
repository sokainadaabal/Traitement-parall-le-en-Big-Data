package org.sid.customerqueryside.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.sid.coreapi.query.GetAllCustmersQuery;
import org.sid.coreapi.query.GetCustmerByIdQuery;
import org.sid.customerqueryside.entities.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/customers/query/")
public class CustomerQueryController {
    private QueryGateway queryGateway;

    @GetMapping("all")
    public CompletableFuture<List<Customer>> customers(){
        log.info("List of customers received...");
        return queryGateway.query(new GetAllCustmersQuery(), ResponseTypes.multipleInstancesOf(Customer.class));
    }

    @GetMapping("{id}")
    public CompletableFuture<Customer> customerById(@PathVariable String id){
        log.info("List of customers received...");
        return queryGateway.query(new GetCustmerByIdQuery(id), ResponseTypes.instanceOf(Customer.class));
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception exception){
        return (ResponseEntity<String>) new ResponseEntity(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
