package com.swt.fahrradshop.bestellung.saga;

import com.swt.fahrradshop.bestellung.command.CancelBestellungCommand;
import com.swt.fahrradshop.bestellung.command.CreateBestellungCommand;
import com.swt.fahrradshop.bestellung.command.UpdatePayedOrSentBestellungCommand;
import com.swt.fahrradshop.bestellung.event.BestellungCanceledEvent;
import com.swt.fahrradshop.bestellung.event.BestellungCreatedEvent;
import com.swt.fahrradshop.bestellung.event.WarenkorbOrderedEvent;
import com.swt.fahrradshop.bestellung.model.BestellungQueryModel;
import com.swt.fahrradshop.bestellung.model.WarenkorbQueryModel;
import com.swt.fahrradshop.bestellung.query.FindBestellungByIdQuery;
import com.swt.fahrradshop.bestellung.query.FindWarenkorbByIdQuery;
import com.swt.fahrradshop.bestellung.valueObject.BestellungsstatusEnum;
import com.swt.fahrradshop.bestellung.valueObject.WarenkorbProdukt;
import com.swt.fahrradshop.core.commands.ProcessZahlungCommand;
import events.ZahlungProcessedEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;
import valueObject.KreditKarte;
import valueObject.ZahlungsstatusEnum;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
@Saga
public class BestellungSaga {

   /* @Autowired
    private transient CommandGateway commandGateway;
    @Autowired
    private transient QueryGateway queryGateway;

    ********************Saga start*********************
    @StartSaga
    @SagaEventHandler(associationProperty = "warenkorbId")
    public void handle(WarenkorbOrderedEvent warenkorbOrderedEvent){

        //get the KundeId from the Warenkob
        FindWarenkorbByIdQuery warenkorbByIdQuery = new FindWarenkorbByIdQuery(warenkorbOrderedEvent.getWarenkorbId());
        //TODO -- Chaouite the query can throw an exception! try catch block needed here
        // the catch should return; = Exit from the handle method and Compensation action should be performed
        WarenkorbQueryModel warenkorb= queryGateway.query(warenkorbByIdQuery, ResponseTypes.instanceOf(WarenkorbQueryModel.class)).join();

        //gesamtpreis mocked - supposed to be calculated in Frontend
        BigDecimal gesamtpreis = BigDecimal.valueOf(1235);

        CreateBestellungCommand createBestellungCommand = new CreateBestellungCommand(
                UUID.randomUUID().toString(),
                BestellungsstatusEnum.ERSTELLT,
                warenkorb.getKundeId(),
                warenkorb.getWarenkorbId(),
                gesamtpreis);

        log.info("WarenkorbOrderedEvent handled for warenkorbId: " + createBestellungCommand.getWarenkorbId() + " and bestellungId: " +
                createBestellungCommand.getBestellungId());


        //-----------------------------------------Send command
        commandGateway.send(createBestellungCommand, (commandResult,throwable)->{
            //-----------exception raised
            if(throwable.isExceptional()){
                log.info("createBestellungCommand was not successful: "+ throwable.exceptionResult().getMessage());

                CancelBestellungCommand cancelBestellungCommand = new CancelBestellungCommand(createBestellungCommand.getBestellungId());
                commandGateway.send(cancelBestellungCommand);
            }
            else{
                log.info("createBestellungCommand was successful! ");
            }
        });
    }


    @SagaEventHandler(associationProperty="bestellungId")
    public void handle(BestellungCreatedEvent bestellungCreatedEvent){
        //get the produkte ids to reserve them
        //get the warenkorb based on id
        FindWarenkorbByIdQuery warenkorbByIdQuery = new FindWarenkorbByIdQuery(bestellungCreatedEvent.getWarenkorbId());
        WarenkorbQueryModel warenkorb= queryGateway.query(warenkorbByIdQuery, ResponseTypes.instanceOf(WarenkorbQueryModel.class)).join();
        //get list of produkt in warenkob
        List<WarenkorbProdukt> produkteList = warenkorb.getProdukteList();

        //TODO: Ayoub-> ReserveProdukteListeCommand + UnreserveProdukteListeCommand
        //--------------One way if we have a list

      ReserveProdukteListeCommand reserveProdukteListeCommand = ReserveProdukteListeCommand(bestellungId,produkteList);
        commandGateway.send(reserveProdukteListeCommand, (commandResult,throwable)->{
            if(throwable != null){
                    log.info("Reserving produkteListe : " + produkteList +
                            " was NOT successful");
                    UnreserveProdukteListeCommand unreserveProdukteListeCommand = new UnreserveProdukteListeCommand(bestellungId,produkteList);
                    commandGateway.send(unreserveProdukteListeCommand);

            }
            else {
                log.info("Reserving produkteListe : " + produkteList +
                        " was successful");
            }
        });

        //--------------other way if we don't have a list Not good to use for loop in saga

        //TODO: Ayoub-> ReserveProduktCommand + UnreserveProduktCommand
        AtomicBoolean cancelReservation = new AtomicBoolean(false);// Mutable boolean reference - to use it inside callback
        // Try to reserve each produkt based on id and anzahl
        for(WarenkorbProdukt produkt: produkteList){
            ReserveProduktCommand reserveProduktCommand = new ReserveProduktCommand(produkt.getProduktId(), produkt.getProduktAnzahl());
            commandGateway.send(reserveProduktCommand, (commandResult,throwable)->{
               //if only one Produkt can't be reserved the bestellung will be canceled
                if(throwable != null){
                    log.info("Produkt with id: "+ produkt.getProduktId() + " could not be reserved! because: "+
                            throwable.exceptionResult().getMessage());
                    cancelReservation.set(true);
                }
            });
        }
        //if only one product could not be reserved, unreserve all the produkte in the Bestellung
        if(cancelReservation.get())
        {
            for(WarenkorbProdukt produkt: produkteList){
                UnreserveProduktCommand unreserveProduktCommand = new UnreserveProduktCommand(produkt.getProduktId(), produkt.getProduktAnzahl());
                commandGateway.send(unreserveProduktCommand);
            }
        }else {
            log.info("Produkte in Bestellung: "+ bestellungCreatedEvent.getBestellungId() + " are reserved");
            log.info("Zahlung for the bestellung: "+ bestellungCreatedEvent.getBestellungId() + " is being processed");
             ProcessZahlungCommand processZahlungCommand = new ProcessZahlungCommand(
                UUID.randomUUID().toString(),
                bestellungCreatedEvent.getBestellungId(),
                bestellungCreatedEvent.getGesamtpreis(),
                keditKarte,
                ZahlungsstatusEnum.IN_BEARBEITUNG
                 );
                 commandGateway.send(processZahlungCommand);
        }
    }


    @SagaEventHandler(associationProperty = "bestellungId")
    public void handle(ProdukteListeReservedEvent   produkteListeReservedEvent){

        FindBestellungByIdQuery bestellungByIdQuery = new FindBestellungByIdQuery(produkteListeReservedEvent.getBestellungId());
        BestellungQueryModel bestellung = queryGateway.query(bestellungByIdQuery,ResponseTypes.instanceOf(BestellungQueryModel.class)).join();


        //mocked keditKarte details - JSON from frontend
        KreditKarte keditKarte = new KreditKarte(
                "1919191919191919",
                "Leo John",
                "08/25",
                "123"
        );
        ProcessZahlungCommand processZahlungCommand = new ProcessZahlungCommand(
                UUID.randomUUID().toString(),
                produkteListeReservedEvent.getBestellungId(),
                bestellung.getGesamtpreis(),
                keditKarte,
                ZahlungsstatusEnum.IN_BEARBEITUNG
        );
        commandGateway.send(processZahlungCommand);
    }
    @SagaEventHandler(associationProperty = "bestellungId")
    public void handle(ZahlungProcessedEvent zahlungProcessedEvent){

        if(zahlungProcessedEvent.getZahlungsstatusEnum().equals(ZahlungsstatusEnum.ABGELEHNT)){
            CancelBestellungCommand cancelBestellungCommand = new CancelBestellungCommand(zahlungProcessedEvent.getBestellungId());
            commandGateway.send(cancelBestellungCommand);
        }

        if(zahlungProcessedEvent.getZahlungsstatusEnum().equals(ZahlungsstatusEnum.BEZAHLT)){
            //Mark bestellung payed = IN_BEARBEITUNG for shipping
            UpdatePayedOrSentBestellungCommand payedBestellungCommand = new UpdatePayedOrSentBestellungCommand(zahlungProcessedEvent.getBestellungId());
            commandGateway.send(payedBestellungCommand);
            //Trigger send Command
            //TODO --Chris:CreateLogistikCommand + CancelLogistikCommand
            CreateLogistikCommand createLogistikCommand = new CreateLogistikCommand(zahlungProcessedEvent.getBestellungId());
            commandGateway.send(createLogistikCommand, (commandMessage,throwable)->{
                if(throwable !=null){
                    log.info("Unable to create the Logitik!!");
                    CancelLogistikCommand cancelLogistikCommand = new CancelLogistikCommand(zahlungProcessedEvent.getBestellungId());
                    commandGateway.send(cancelLogistikCommand);
                }
                else{
                    log.info("Logistik created successfully!");
                }
            });
        }
    }

    @SagaEventHandler(associationProperty = "bestellungId")
    public void handle(LogistikCreatedEvent logistikCreatedEvent){
        SendLogistikCommand sendLogistikCommand = new SendLogistikCommand(logistikCreatedEvent.getBestellungId());
        commandGateway.send(sendLogistikCommand, ((commandMessage, throwable) -> {
            if(throwable != null){
                log.info("Stock issues!!");
                CancelLogistikCommand cancelLogistikCommand = new CancelLogistikCommand(zahlungProcessedEvent.getBestellungId());
                commandGateway.send(cancelLogistikCommand);
            }
            else{
                log.info("Bestellung on its way.");
            }
        }));
    }

    ********************Saga Ends*********************
    @EndSaga
    @SagaEventHandler(associationProperty = "bestellungId")
    public void handle(LogistikSentEvent logistikSentEvent){
        UpdatePayedOrSentBestellungCommand sentBestellungCommand = new UpdatePayedOrSentBestellungCommand(logistikSentEvent.getBestellungId());
        commandGateway.send(sentBestellungCommand);
        log.info("Bestellung Saga successfully finished for: "+logistikSentEvent.getBestellungId());
    }
    @EndSaga
    @SagaEventHandler(associationProperty = "bestellungId")
    public void handle(LogistikCanceledEvent logistikCanceledEvent){
        log.info("Bestellung Saga aborted for: "+logistikCanceledEvent.getBestellungId() + ". Unable to create a Logistik");
    }

    @EndSaga
    @SagaEventHandler(associationProperty="bestellungId")
    public void handle(BestellungCanceledEvent bestellungCanceledEvent){
        log.info("Bestellung Saga aborted for: "+ bestellungCanceledEvent.getBestellungId());
    }

    @EndSaga
    @SagaEventHandler(associationProperty = "bestellungId")
    public void handle(ProdukteListeUnreservedEvent produkteListeUnreservedEvent){
        log.info("Bestellung Saga aborted for: "+ produkteListeUnreservedEvent.getBestellungId()+
                ". Unable to reserve Produkte.");
    }
*/
}
