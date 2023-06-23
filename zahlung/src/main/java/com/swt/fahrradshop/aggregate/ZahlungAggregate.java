package com.swt.fahrradshop.aggregate;

import com.swt.fahrradshop.core.commands.ProcessZahlungCommand;
import events.ZahlungProcessedEvent;
import valueObject.KreditKarte;
import valueObject.ZahlungsstatusEnum;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import static org.axonframework.modelling.command.AggregateLifecycle.apply;
import org.axonframework.spring.stereotype.Aggregate;
import java.math.BigDecimal;
import java.util.Random;

@Aggregate
public class ZahlungAggregate {

    @AggregateIdentifier
    private  String zahlungId;
    private  String bestellungId;
    private  BigDecimal gesamtpreis;
    private KreditKarte kreditKarte;
    private ZahlungsstatusEnum zahlungsstatusEnum;

    public ZahlungAggregate() {
    }

    @CommandHandler
    public ZahlungAggregate(ProcessZahlungCommand cmd){
        //Mock the approval or denial of the payment
        Random random = new Random();
        boolean paymentApproved = random.nextBoolean();

        if(paymentApproved){
            apply(new ZahlungProcessedEvent(
                    cmd.getZahlungId(),
                    cmd.getBestellungId(),
                    cmd.getGesamtpreis(),
                    cmd.getKreditKarte(),
                    ZahlungsstatusEnum.BEZAHLT
            ));
        }else{
            apply(new ZahlungProcessedEvent(
                    cmd.getZahlungId(),
                    cmd.getBestellungId(),
                    cmd.getGesamtpreis(),
                    cmd.getKreditKarte(),
                    ZahlungsstatusEnum.ABGELEHNT
            ));
        }
    }
    @EventSourcingHandler
    public void on(ZahlungProcessedEvent  evt){
            this.zahlungId =  evt.getZahlungId();
            this.bestellungId = evt.getBestellungId();
            this.gesamtpreis = evt.getGesamtpreis();
            this.kreditKarte = evt.getKreditKarte();
            this.zahlungsstatusEnum = evt.getZahlungsstatusEnum();
    }
}
