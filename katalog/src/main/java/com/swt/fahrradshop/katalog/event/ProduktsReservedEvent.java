package com.swt.fahrradshop.katalog.event;

import com.swt.fahrradshop.katalog.valueObject.ProduktDetails;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.UUID;
@Data
@AllArgsConstructor
public class ProduktsReservedEvent {
    private UUID bestellungId;
    private List<ProduktDetails> produktDetails;
}
