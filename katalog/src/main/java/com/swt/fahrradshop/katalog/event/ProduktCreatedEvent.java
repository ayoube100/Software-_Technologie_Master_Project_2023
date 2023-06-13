package com.swt.fahrradshop.katalog.event;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
// this event will notify that a ProduktCreateCommand is received
public class ProduktCreatedEvent {
    @TargetAggregateIdentifier
    private UUID produktId;
    private String Name;
    private BigDecimal Price;


}