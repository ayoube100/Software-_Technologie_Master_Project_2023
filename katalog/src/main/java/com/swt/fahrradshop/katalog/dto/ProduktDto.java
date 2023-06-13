package com.swt.fahrradshop.katalog.dto;

import lombok.Value;

import java.math.BigDecimal;
@Value

public class ProduktDto {

    private String name;
    private BigDecimal price;
}
