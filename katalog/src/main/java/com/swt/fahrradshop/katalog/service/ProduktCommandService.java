package com.swt.fahrradshop.katalog.service;

import com.swt.fahrradshop.katalog.command.CreateProduktCommand;
import com.swt.fahrradshop.katalog.command.DeleteProduktCommand;
import com.swt.fahrradshop.katalog.command.UpdateProduktCommand;
import com.swt.fahrradshop.katalog.dto.ProduktDto;
import com.swt.fahrradshop.katalog.entity.Produkt;
import com.swt.fahrradshop.katalog.valueObject.Kategorie;
import lombok.AllArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;



import java.util.UUID;
import java.util.concurrent.CompletableFuture;


@Service
@AllArgsConstructor
public class ProduktCommandService {
    //CommandGateway for dispatching commands
    private final CommandGateway commandGateway;

    public CompletableFuture<Produkt> createProdukt (ProduktDto produktdto) {
        return this.commandGateway.send(new CreateProduktCommand(
                UUID.randomUUID(),
                produktdto.getName(),
                produktdto.getPreis(),
                produktdto.getAnzahl(),
                produktdto.getKategorie(),
                produktdto.getVerfuegbarkeit()
        ));

    }

    public CompletableFuture<String> updateProdukt(UUID produktId,ProduktDto produktdto)
                                                           {
        return this.commandGateway.send(new UpdateProduktCommand(
                produktId,
                produktdto.getName(),
                produktdto.getPreis(),
                produktdto.getAnzahl(),
                produktdto.getKategorie(),
                produktdto.getVerfuegbarkeit()
        ));
    }
    public CompletableFuture<String> deleteProdukt(UUID produktId) {
        return commandGateway.send(new DeleteProduktCommand(produktId));
    }
}

