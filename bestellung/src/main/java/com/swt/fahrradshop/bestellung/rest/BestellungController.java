package com.swt.fahrradshop.bestellung.rest;

import com.swt.fahrradshop.bestellung.command.CreateBestellungCommand;
import com.swt.fahrradshop.bestellung.entity.BestellungEntity;
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

        CreateBestellungCommand cmd = CreateBestellungCommand.builder()
                .bestellungId(UUID.randomUUID().toString())
               .bestellungsstatusEnum(BestellungsstatusEnum.ERSTELLT)
                .kundenIdValueObject(bestellung.getKundenIdValueObject())
                .build();

        String returnedValue;
        try{
            commandGateway.send(cmd);
            returnedValue = cmd.getBestellungId() + "\n " + cmd.getBestellungsstatusEnum().toString() +"\n"+
                    cmd.getBestellungsstatusEnum() +"\n"+
                 cmd.getKundenIdValueObject() +"\n";
        }catch(Exception e){
             returnedValue = "error";
        }
        return returnedValue ;
    }
}
