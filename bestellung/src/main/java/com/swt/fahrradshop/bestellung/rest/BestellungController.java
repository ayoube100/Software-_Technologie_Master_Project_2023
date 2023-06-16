package com.swt.fahrradshop.bestellung.rest;

import com.swt.fahrradshop.bestellung.command.CancelBestellungCommand;
import com.swt.fahrradshop.bestellung.command.CreateBestellungCommand;
import com.swt.fahrradshop.bestellung.command.UpdatePayedOrSentBestellungCommand;
import com.swt.fahrradshop.bestellung.model.BestellungModel;
import com.swt.fahrradshop.bestellung.valueObject.BestellungsstatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController("/bestellung")
@Slf4j
public class BestellungController {

    private final CommandGateway commandGateway;
    public BestellungController(CommandGateway cmd)
    {
        this.commandGateway = cmd;
    }

    @PostMapping("/create")
    public String createBestellung (@RequestBody BestellungModel bestellung){
        //put payload coming from Frontend in Command
        CreateBestellungCommand cmd = CreateBestellungCommand.builder()
                .bestellungId(UUID.randomUUID().toString())
                .bestellungsstatus(BestellungsstatusEnum.ERSTELLT)
                .kundeId(bestellung.getKundeId())
                .warenkorbId(bestellung.getWarenkorbId())
                .gesamtpreis(bestellung.getGesamtpreis())
                .build();
        //for checking reasons
        String returnedValue;
        try{
            //send command to gateway and trigger CommandHandler
            commandGateway.send(cmd);
            returnedValue = cmd.getBestellungId() + "\n " + cmd.getBestellungsstatus().toString() +"\n"+ cmd.getKundeId() +"\n"+
                    cmd.getWarenkorbId() +"\n"+ cmd.getGesamtpreis() +"\n";
        }catch(Exception e){
             returnedValue = "error while creating the bestellung";
        }
        return returnedValue ;
    }
    @DeleteMapping("/cancel/{bestellungId}")
    public CompletableFuture<String> cancelBestellung(@PathVariable String bestellungId){
        CancelBestellungCommand cmd = CancelBestellungCommand.builder().bestellungId(bestellungId).build();
        return commandGateway.send(cmd);
    }

    // we should speficy how it would be changed = the status

    @PutMapping("/payed/{bestellungId}")
    public CompletableFuture<String> updatePayedBestellungStatus(@PathVariable String bestellungId){
        UpdatePayedOrSentBestellungCommand cmd = UpdatePayedOrSentBestellungCommand.builder().bestellungId(bestellungId).build();
        return commandGateway.send(cmd);
    }

    @PutMapping("/sent/{bestellungId}")
    public CompletableFuture<String> updateSentBestellungStatus(@PathVariable String bestellungId){
        UpdatePayedOrSentBestellungCommand cmd = UpdatePayedOrSentBestellungCommand.builder().bestellungId(bestellungId).build();
        return commandGateway.send(cmd);
    }
}
