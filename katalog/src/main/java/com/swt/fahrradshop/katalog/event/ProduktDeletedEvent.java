package com.swt.fahrradshop.katalog.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProduktDeletedEvent {

    private String produktId;
}
