package com.swt.fahrradshop.katalog.event;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProduktUnreservedEvent {
    private String produktId;
    private Integer AnzahlToReserve;
}
