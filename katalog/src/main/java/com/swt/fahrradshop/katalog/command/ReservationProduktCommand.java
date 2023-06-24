package com.swt.fahrradshop.katalog.command;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReservationProduktCommand {
    private String produktId;
    private Integer AnzahlToReserve;

    
}
