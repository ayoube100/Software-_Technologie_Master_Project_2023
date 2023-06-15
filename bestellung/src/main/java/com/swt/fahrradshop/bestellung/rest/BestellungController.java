package com.swt.fahrradshop.bestellung.rest;

import com.swt.fahrradshop.bestellung.command.CreateBestellungCommand;
import com.swt.fahrradshop.bestellung.model.BestellungModel;
import com.swt.fahrradshop.bestellung.valueObject.BestellungsstatusEnum;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
public class BestellungController {

    private final CommandGateway commandGateway;
    public BestellungController(CommandGateway cmd)
    {
        this.commandGateway = cmd;
    }

    @PostMapping("/bestellung/create")
    public String createBestellung (@RequestBody BestellungModel bestellung){
        //put payload coming from Frontend in Command
        CreateBestellungCommand cmd = CreateBestellungCommand.builder()
                .bestellungId(UUID.randomUUID().toString())
                .bestellungsstatusEnum(BestellungsstatusEnum.ERSTELLT)
                .kundeIdValueObject(bestellung.getKundeIdValueObject())
                .warenkorbId(bestellung.getWarenkorbId())
                .gesamtpreis(bestellung.getGesamtpreis())
                .build();

        String returnedValue;
        try{
            //send command to gateway and trigger CommandHandler
            commandGateway.send(cmd);
            returnedValue = cmd.getBestellungId() + "\n " + cmd.getBestellungsstatusEnum().toString() +"\n"+ cmd.getKundeIdValueObject() +"\n"+
                    cmd.getWarenkorbId() +"\n"+ cmd.getGesamtpreis() +"\n";
        }catch(Exception e){
             returnedValue = "error";
        }
        return returnedValue ;
    }
}
