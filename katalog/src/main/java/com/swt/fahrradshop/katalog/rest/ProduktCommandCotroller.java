package com.swt.fahrradshop.katalog.rest;



import com.swt.fahrradshop.katalog.command.ReservationProduktsCommand;
import com.swt.fahrradshop.katalog.command.UpdateProduktCommand;
import com.swt.fahrradshop.katalog.dto.ProduktDto;
import com.swt.fahrradshop.katalog.entity.Produkt;
import com.swt.fahrradshop.katalog.service.ProduktCommandService;
import com.swt.fahrradshop.katalog.valueObject.Kategorie;
import com.swt.fahrradshop.katalog.valueObject.Verfuegbarkeit;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@AllArgsConstructor
@RequestMapping
//Craeting REST API for the Commands
public class ProduktCommandCotroller {
    private final ProduktCommandService produktCommand;


    @PostMapping("/katalog/create")
    @ResponseStatus(value = HttpStatus.CREATED)
    public CompletableFuture<Produkt> createProdukt(@RequestBody ProduktDto produktDto) {
        return produktCommand.createProdukt(produktDto);
    }



    @PutMapping("/katalog/update/{produktId}")
    public CompletableFuture <Produkt> updateProdukt(@PathVariable UUID produktId, @RequestBody ProduktDto produktDto) {

        return produktCommand.updateProdukt(produktId, produktDto);
    }

@PostMapping("/katalog/reserve")
public ResponseEntity<String> reserveProducts(@RequestBody ReservationProduktsCommand command) {
    produktCommand.reserveProdukts(command);
    return ResponseEntity.ok("Products reserved successfully");
}
}






