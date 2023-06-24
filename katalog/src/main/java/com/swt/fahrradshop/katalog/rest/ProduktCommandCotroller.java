package com.swt.fahrradshop.katalog.rest;



import com.swt.fahrradshop.katalog.dto.ProduktDto;
import com.swt.fahrradshop.katalog.entity.Produkt;
import com.swt.fahrradshop.katalog.service.ProduktCommandService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@AllArgsConstructor
@RequestMapping
//Craeting REST API for the Commands
public class ProduktCommandCotroller {
    private final ProduktCommandService produktcommandService;
    @PostMapping("/katalog/create")
    @ResponseStatus(value = HttpStatus.CREATED)
    public CompletableFuture<Produkt> createProdukt(@RequestBody ProduktDto produktDto){
        return this.produktcommandService.createProdukt(produktDto);
    }




}

