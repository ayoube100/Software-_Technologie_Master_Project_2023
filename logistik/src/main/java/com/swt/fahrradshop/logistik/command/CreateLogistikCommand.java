package com.swt.fahrradshop.logistik.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.UUID;
@Data
@AllArgsConstructor
public class CreateLogistikCommand {

    @TargetAggregateIdentifier
    private UUID produktId;
}

