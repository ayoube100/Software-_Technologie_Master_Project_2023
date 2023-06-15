package com.swt.fahrradshop.bestellung.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
@AllArgsConstructor
public class CreateWarenkorbCommand {
    @TargetAggregateIdentifier
    private final String warenkorbId;

    private final String KundeId;

}
