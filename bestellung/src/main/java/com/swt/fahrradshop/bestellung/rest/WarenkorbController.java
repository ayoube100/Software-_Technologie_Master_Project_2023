package com.swt.fahrradshop.bestellung.rest;


import com.google.gson.*;
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

        /*
                Parse the request body that is a String JSON to a String
                from:
                {
                    "kundeId": "Leo"
                }
                to:
                Leo
        */
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = jsonParser.parse(kundeIdJSON).getAsJsonObject();

        String kundeId = jsonObject.get("kundeId").getAsString();

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
