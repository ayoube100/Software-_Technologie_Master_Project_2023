package com.swt.fahrradshop.bestellung.event;

        import com.swt.fahrradshop.bestellung.valueObject.BestellungsstatusEnum;
        import lombok.AllArgsConstructor;
        import lombok.Builder;
        import lombok.Data;
        import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
@AllArgsConstructor
public class PayedOrSentBestellungUpdatedEvent {
    @TargetAggregateIdentifier
    private String bestellungId;
    private final BestellungsstatusEnum bestellungsstatusEnum;
}
