package com.swt.fahrradshop.katalog.rest;



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
        return this.produktCommand.createProdukt(produktDto);

    }

    @PutMapping ("/katalog/update/{produktId}")


    public ResponseEntity<String> updateProdukt(@PathVariable UUID produktId, @RequestBody ProduktDto produktDto) {

        try {
            produktCommand.updateProdukt(produktId, produktDto);
            return ResponseEntity.ok("Produkt Succuessfully Updated");

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Produkt not updated");
        }


    }
    @DeleteMapping("/katalog/delete/{produktId}")
    public ResponseEntity<String> deleteProdukt(@PathVariable UUID produktId) {
        try {
            produktCommand.deleteProdukt(produktId);
            return ResponseEntity.ok("Produkt successfully deleted");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to delete Produkt");
        }
    }
}






