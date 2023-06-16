package com.swt.fahrradshop.bestellung.rest;

import com.swt.fahrradshop.bestellung.BestellungApplication;
import com.swt.fahrradshop.bestellung.command.CreateWarenkorbCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class WarenkorbController {

    private final CommandGateway commandGateway;

    public WarenkorbController(CommandGateway cmd) {
        this.commandGateway = cmd;
    }

    @PostMapping("warenkorb/create")
    public String createWarenkorb(@RequestBody String kundeIdJSON){


        String kundeId = BestellungApplication.JSONStringTOString(kundeIdJSON,"kundeId");

        CreateWarenkorbCommand cmd = CreateWarenkorbCommand.builder()
                .warenkorbId(UUID.randomUUID().toString())
                .KundeId(kundeId)
                .build();

        String returnedValue;
        try{
            //send command to gateway and trigger CommandHandler
            commandGateway.send(cmd);
            //TODO -- Chaouite:  KundeId is registerd as a JSON type, fix it
            returnedValue = cmd.getWarenkorbId() + "\n "+ cmd.getKundeId() ;
        }catch(Exception e){
            returnedValue = "error";
        }
        return returnedValue ;
    }
}
