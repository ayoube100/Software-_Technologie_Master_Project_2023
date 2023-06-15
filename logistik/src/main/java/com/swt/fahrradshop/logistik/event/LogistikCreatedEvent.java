package com.swt.fahrradshop.logistik.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
// this event will notify that a ProduktCreateCommand is received
public class LogistikCreatedEvent {
    private UUID logistikId;
}
