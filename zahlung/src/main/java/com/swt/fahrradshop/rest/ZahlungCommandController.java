package com.swt.fahrradshop.rest;


import com.swt.fahrradshop.core.commands.ProcessZahlungCommand;
import valueObject.ZahlungsstatusEnum;
import com.swt.fahrradshop.model.ZahlungCommandModel;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
public class ZahlungCommandController {


    private final CommandGateway commandGateway;

    public ZahlungCommandController(CommandGateway commandGateway) {

        this.commandGateway = commandGateway;
    }

    @PostMapping("/zahlung/process")
    public Mono<ResponseEntity<String>> processZahlung(@RequestBody ZahlungCommandModel zahlung){
        return Mono.fromCallable(() -> {
            ProcessZahlungCommand cmd = ProcessZahlungCommand.builder()
                    .zahlungId(UUID.randomUUID().toString())
                    .bestellungId(zahlung.getBestellungId())
                    .gesamtpreis(zahlung.getGesamtpreis())
                    .kreditKarte(zahlung.getKreditKarte())
                    .zahlungsstatusEnum(ZahlungsstatusEnum.IN_BEARBEITUNG)
                    .build();
            commandGateway.send(cmd);
            return ResponseEntity.ok("Zahlung "+
                    cmd.getZahlungId()+ " is being processed.");
        });

    }

}
