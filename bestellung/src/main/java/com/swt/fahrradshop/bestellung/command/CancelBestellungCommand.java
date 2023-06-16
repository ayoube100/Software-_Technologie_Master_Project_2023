package com.swt.fahrradshop.bestellung.command;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
@AllArgsConstructor
public class CancelBestellungCommand {
    //final because it's a read only class
    @TargetAggregateIdentifier
    private  final String bestellungId;

}
