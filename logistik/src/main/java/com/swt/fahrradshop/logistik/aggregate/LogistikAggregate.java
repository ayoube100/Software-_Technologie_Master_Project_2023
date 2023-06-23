package com.swt.fahrradshop.logistik.aggregate;

import com.swt.fahrradshop.logistik.command.CancelLogistikCommand;
import com.swt.fahrradshop.logistik.command.CreateLogistikCommand;
import com.swt.fahrradshop.logistik.command.SendShippingCommand;
import com.swt.fahrradshop.logistik.event.LogistikCanceledEvent;
import com.swt.fahrradshop.logistik.event.LogistikCreatedEvent;
import com.swt.fahrradshop.logistik.event.SendShippingEvent;
import com.swt.fahrradshop.logistik.valueObject.*;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import java.math.BigDecimal;
import static org.axonframework.modelling.command.AggregateLifecycle.apply;
import static org.axonframework.modelling.command.AggregateLifecycle.markDeleted;

@Aggregate
@Slf4j
public class LogistikAggregate {

    @AggregateIdentifier
    private String logistikId;
    private LieferstatusEnum lieferstatusEnum;
    private String bestellungId;

    public LogistikAggregate() {
    }

    /**
     * create a new Logistik
     **/
    //triggered when commandGateway saved command
    @CommandHandler
    public LogistikAggregate(CreateLogistikCommand cmd) {
        apply( new LogistikCreatedEvent());
    }

    /**
     * Cancel a Logistik
     **/
    @CommandHandler
    public void handle(CancelLogistikCommand cmd) {

        apply(new LogistikCanceledEvent(cmd.getLogistikId()));
    }

    @CommandHandler
    public void handle(SendShippingCommand cmd){
        if(this.lieferstatusEnum.toString().equals("BEARBEITET"))
        apply(new SendShippingEvent(
                cmd.getLogistikId(),
                LieferstatusEnum.VERSENDET));
        else {
            apply(new SendShippingEvent(
                    cmd.getLogistikId(),
                    LieferstatusEnum.STORNIERT));
        }
    }

    //triggered when event dispatched => actualize the state of the aggregate
    @EventSourcingHandler
    public void on(LogistikCreatedEvent evt) throws Exception {
        this.logistikId = evt.getLogistikId();
        this.lieferstatusEnum = evt.getLieferstatus();
        this.bestellungId = evt.getBestellungId();
    }

    @EventSourcingHandler
    public void on(LogistikCanceledEvent evt) {
        markDeleted();
    }

    @EventSourcingHandler
    public void on(SendShippingEvent evt){
        this.logistikId = evt.getLogistikId();
        this.lieferstatusEnum = evt.getLieferstatusEnum();
    }
}
