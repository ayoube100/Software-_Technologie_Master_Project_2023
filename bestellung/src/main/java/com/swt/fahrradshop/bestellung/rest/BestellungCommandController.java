package com.swt.fahrradshop.bestellung.rest;

import com.swt.fahrradshop.bestellung.command.CancelBestellungCommand;
import com.swt.fahrradshop.bestellung.command.CreateBestellungCommand;
import com.swt.fahrradshop.bestellung.command.UpdatePayedOrSentBestellungCommand;
import com.swt.fahrradshop.bestellung.model.BestellungCommandModel;
import com.swt.fahrradshop.bestellung.valueObject.BestellungsstatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@Slf4j
public class BestellungCommandController {

    private final CommandGateway commandGateway;
    public BestellungCommandController(CommandGateway cmd)
    {
        this.commandGateway = cmd;
    }

    @PostMapping("/bestellung/create")
    public void createBestellung (@RequestBody BestellungCommandModel bestellung){
        //put payload coming from Frontend in Command
        CreateBestellungCommand cmd = CreateBestellungCommand.builder()
                .bestellungId(UUID.randomUUID().toString())
                .bestellungsstatus(BestellungsstatusEnum.ERSTELLT)
                .kundeId(bestellung.getKundeId())
                .warenkorbId(bestellung.getWarenkorbId())
                .gesamtpreis(bestellung.getGesamtpreis())
                .build();
        commandGateway.send(cmd);
    }
    @DeleteMapping("/bestellung/cancel/{bestellungId}")
    public CompletableFuture<String> cancelBestellung(@PathVariable String bestellungId){
        CancelBestellungCommand cmd = CancelBestellungCommand.builder().bestellungId(bestellungId).build();
        return commandGateway.send(cmd);
    }

    @PutMapping("/bestellung/payed/{bestellungId}")
    public CompletableFuture<String> updatePayedBestellungStatus(@PathVariable String bestellungId){
        UpdatePayedOrSentBestellungCommand cmd = UpdatePayedOrSentBestellungCommand.builder().bestellungId(bestellungId).build();
        return commandGateway.send(cmd);
    }

    @PutMapping("/bestellung/sent/{bestellungId}")
    public CompletableFuture<String> updateSentBestellungStatus(@PathVariable String bestellungId){
        UpdatePayedOrSentBestellungCommand cmd = UpdatePayedOrSentBestellungCommand.builder().bestellungId(bestellungId).build();
        return commandGateway.send(cmd);
    }
}
