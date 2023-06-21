package com.swt.fahrradshop.logistik.aggregate;

import com.swt.fahrradshop.logistik.command.CreateLogistikCommand;
import com.swt.fahrradshop.logistik.event.LogistikCreatedEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Aggregate
//this aggregate receives and handles the Commands and for every Command will dispatch a Query.
public class LogistikAggregate {
    @AggregateIdentifier
    private UUID logistikId;


    @CommandHandler
    public LogistikAggregate(CreateLogistikCommand cmd){

        LogistikCreatedEvent evt = LogistikCreatedEvent.builder()

        AggregateLifecycle.apply(evt);
        );
    }
    
    //triggered when event dispatched => actualize the state of the aggregate
    @EventSourcingHandler
    public void on(LogistikCreatedEvent evt) throws Exception{
        this.logistikId = evt.getLogistikId();
    }


}
