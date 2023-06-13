package com.swt.fahrradshop.katalog.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.math.BigDecimal;
import java.util.UUID;
@Data
@AllArgsConstructor
public class CreateProduktCommand {

    @TargetAggregateIdentifier
    private UUID produktId;
    private String Name;
    private BigDecimal Price;
}
