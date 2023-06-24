package com.swt.fahrradshop.katalog.event;

import com.swt.fahrradshop.katalog.valueObject.ProduktDetails;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
@Data
@AllArgsConstructor
public class ProduktUnreservedEvent {
    private String produktId;
    private Integer AnzahlToReserve;
}
