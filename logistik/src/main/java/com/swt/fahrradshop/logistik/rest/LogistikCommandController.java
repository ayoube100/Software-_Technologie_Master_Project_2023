package com.swt.fahrradshop.logistik.rest;

import com.swt.fahrradshop.logistik.dto.LogistikDto;
import com.swt.fahrradshop.logistik.entity.Logistik;
import com.swt.fahrradshop.logistik.service.LogistikCommandService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@AllArgsConstructor
@RequestMapping
//Craeting REST API for the Commands
public class LogistikCommandController {
    private final LogistikCommandService logistikCommandService;
    @PostMapping("/logistik/create")
    @ResponseStatus(value = HttpStatus.CREATED)
    public CompletableFuture<Logistik> createLogistik(@RequestBody LogistikDto logistikDto){
        return this.logistikCommandService.createLogistik(logistikDto);
    }




}

