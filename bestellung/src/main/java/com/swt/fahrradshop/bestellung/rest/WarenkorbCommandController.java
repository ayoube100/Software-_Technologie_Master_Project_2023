package com.swt.fahrradshop.bestellung.rest;

import com.swt.fahrradshop.bestellung.command.AddProduktToWarenkorbCommand;
import com.swt.fahrradshop.bestellung.command.CreateWarenkorbCommand;
import com.swt.fahrradshop.bestellung.command.DeleteProduktFromWarenkorbCommand;
import com.swt.fahrradshop.bestellung.command.OrderWarenkorbCommand;
import com.swt.fahrradshop.bestellung.valueObject.WarenkorbProdukt;
import com.swt.fahrradshop.bestellung.valueObject.WarenkorbStatusEnum;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class WarenkorbCommandController {

    private final CommandGateway commandGateway;

    public WarenkorbCommandController(CommandGateway cmd) {
        this.commandGateway = cmd;
    }

    @PostMapping("/warenkorb/create/{kundeId}")
    public void createWarenkorb(@PathVariable String kundeId){
        List<WarenkorbProdukt> products = new ArrayList<>();
        WarenkorbProdukt initial = new WarenkorbProdukt("ppp",2);
        products.add(initial);
        CreateWarenkorbCommand cmd = CreateWarenkorbCommand.builder()
                .warenkorbId(UUID.randomUUID().toString())
                .KundeId(kundeId)
                .produkte(products)
                .warenkorbStatus(WarenkorbStatusEnum.NICHT_BESTELLT)
                .build();
        commandGateway.send(cmd);
    }

    @PutMapping("/warenkorb/{warenkorbtId}/add/{produktId}/{anzahl}")
    public void addProduktToWarenkorb(@PathVariable String warenkorbtId,
                                      @PathVariable String produktId,
                                      @PathVariable Integer anzahl ){
        AddProduktToWarenkorbCommand cmd = new AddProduktToWarenkorbCommand(
                                                warenkorbtId,
                                                produktId,
                                                anzahl);
        commandGateway.send(cmd);
    }

    //delete only by one
    @PutMapping("/warenkorb/{warenkorbtId}/delete/{produktId}")
    public void deleteProduktToWarenkorb(@PathVariable String warenkorbtId,
                                      @PathVariable String produktId){
        DeleteProduktFromWarenkorbCommand cmd = new DeleteProduktFromWarenkorbCommand(
                warenkorbtId,
                produktId);
        commandGateway.send(cmd);
    }

    @PutMapping("/warenkorb/{warenkorbId}/order")
    public void orderWarenkorb(@PathVariable String warenkorbId){
        OrderWarenkorbCommand cmd = new OrderWarenkorbCommand(warenkorbId);
        commandGateway.send(cmd);
    }


}
