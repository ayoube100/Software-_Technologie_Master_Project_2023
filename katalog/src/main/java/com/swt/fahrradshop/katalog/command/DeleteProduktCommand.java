package com.swt.fahrradshop.katalog.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class DeleteProduktCommand {
    private String produktId;
}
