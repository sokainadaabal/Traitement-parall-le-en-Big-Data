package org.sid.customercommandside.aggregates;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.sid.coreapi.commands.CreateCustomerCommand;
import org.sid.coreapi.events.CustomerCreatedEvent;

@Aggregate
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class CustomerAggregate {
    @AggregateIdentifier
    private String cutomerId;
    private String name;
    private String email;

    @CommandHandler
    public CustomerAggregate(CreateCustomerCommand command){
        log.info("CustomerCreatedEvent received...");
        AggregateLifecycle.apply(
                new CustomerCreatedEvent(
                        command.getId(), command.getName(), command.getEmail()
                )
        );
    }
    @EventSourcingHandler
    public void on(CustomerCreatedEvent event ){
        log.info("CustomerCreatedEvent occurred...");
     this.cutomerId= event.getId();
     this.name=event.getName();
     this.email= event.getEmail();
    }
}
