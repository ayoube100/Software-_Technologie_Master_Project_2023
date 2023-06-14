package com.swt.fahrradshop.bestellung.rest;

import com.swt.fahrradshop.bestellung.command.CreateBestellungCommand;
import com.swt.fahrradshop.bestellung.entity.BestellungEntity;
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

    @PostMapping("/bestellung")
    public String createBestellung (@RequestBody BestellungEntity bestellung){

        CreateBestellungCommand cmd = CreateBestellungCommand.builder()
                .bestellungId(UUID.randomUUID().toString())
                .bestellungsstatus(bestellung.getBestellungsstatus())
                .einzelposten(bestellung.getEinzelposten())
                .kundenIdValueObject(bestellung.getKundenIdValueObject())
                .zahlungValueObject(bestellung.getZahlungValueObject())
                .build();

        String returnedValue;
        try{
            //bestellungId returned to check the event source in Axon framework
             returnedValue = cmd.getBestellungId() + String.valueOf(commandGateway.send(cmd))+"\n"+
                     cmd.getBestellungId() + "\n" +  cmd.getBestellungsstatus() +"\n"+
                     cmd.getEinzelposten() +"\n"+ cmd.getKundenIdValueObject() +"\n"+ cmd.getZahlungValueObject();
        }catch(Exception e){
             returnedValue = " error";
        }
        return returnedValue ;
    }
}
