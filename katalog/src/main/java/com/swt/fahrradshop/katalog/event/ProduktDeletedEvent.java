package com.swt.fahrradshop.katalog.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProduktDeletedEvent {

    private String produktId;
}
