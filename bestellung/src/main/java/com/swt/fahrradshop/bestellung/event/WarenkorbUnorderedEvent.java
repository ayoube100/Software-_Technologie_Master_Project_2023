package com.swt.fahrradshop.bestellung.event;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WarenkorbUnorderedEvent {
    private String warenkorbId;
}
