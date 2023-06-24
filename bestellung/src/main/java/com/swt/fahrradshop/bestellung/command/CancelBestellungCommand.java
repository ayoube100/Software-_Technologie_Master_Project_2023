package com.swt.fahrradshop.bestellung.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
@AllArgsConstructor
public class CancelBestellungCommand {
    @TargetAggregateIdentifier
    private final String bestellungId;
}
