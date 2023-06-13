package com.swt.fahrradshop.bestellung.rest;

import com.swt.fahrradshop.bestellung.command.CreateBestellungCommand;
import com.swt.fahrradshop.bestellung.model.BestellungModel;
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
    public String createBestellung (@RequestBody BestellungModel bestellung){

        CreateBestellungCommand cmd = CreateBestellungCommand.builder()
                .bestellungId(UUID.randomUUID().toString())
                .bestellungsstatus(bestellung.getBestellungsstatus())
                .einzelposten(bestellung.getEinzelposten())
                .kundenIdValueObject(bestellung.getKundenIdValueObject())
                .zahlung(bestellung.getZahlung())
                .build();

        String returnedValue;
        try{
            //bestellungId returned to check the event source in Axon framework
             returnedValue = cmd.getBestellungId() + String.valueOf(commandGateway.send(cmd));
        }catch(Exception e){
             returnedValue = " error";
        }
        return returnedValue ;
    }
}
