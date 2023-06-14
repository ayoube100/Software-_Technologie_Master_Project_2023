package com.swt.fahrradshop.katalog.dto;

import lombok.Value;

import java.math.BigDecimal;
@Value

public class ProduktDto {

    private String Name;
    private BigDecimal Price;
}
