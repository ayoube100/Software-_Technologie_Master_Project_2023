package com.swt.fahrradshop.katalog.rest;

import com.swt.fahrradshop.katalog.entity.Produkt;
import com.swt.fahrradshop.katalog.service.ProduktQueryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value = "katalog/produkt")
@AllArgsConstructor
public class ProduktQueryController {
    private final ProduktQueryService produktQueryService;

    @GetMapping(value = "/{produktId}")
    public CompletableFuture<Produkt> findById(@PathVariable("produktId") String produktId) {
        return this.produktQueryService.findById(produktId);
    }

    @GetMapping("/{accountId}/events")
    public List<Object> listEventsForProdukt(@PathVariable(value = "produktId") String produktId) {
        return this.produktQueryService.listEventsForAccount(produktId);
    }



}
