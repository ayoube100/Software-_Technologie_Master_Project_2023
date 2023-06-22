package com.swt.fahrradshop.katalog.command;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;
@Data
@AllArgsConstructor
public class DeleteProduktCommand {
    private UUID produktId;
}
