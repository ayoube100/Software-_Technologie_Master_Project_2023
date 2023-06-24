package com.swt.fahrradshop.bestellung.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BestellungCanceledEvent {
    //@TargetAggregateIdentifier
    private String bestellungId;
}
