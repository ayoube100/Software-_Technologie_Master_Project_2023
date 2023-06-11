package com.swt.fahrradshop.katalog.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/katalog")
public class KatalogController {

    @Autowired
    private Environment env;

    @PostMapping
    public String addToKatalog (){
        return "add to katalog";
    }

    @DeleteMapping
    public String deleteFromKatalog(){
        return "delete from katalog";
    }

    @GetMapping
    public String showKatalog(){
        return "show Katalog " + env.getProperty("local.server.port");
    }

    @PutMapping
    public String updateProduktInKatalog(){
        return "update Product";
    }
}
