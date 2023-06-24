package com.swt.fahrradshop.bestellung.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FindBestellungByIdQuery {
    private String bestellungToBeFoundId;
}
