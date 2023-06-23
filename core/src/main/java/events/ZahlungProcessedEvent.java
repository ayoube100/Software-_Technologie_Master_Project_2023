package events;


import valueObject.KreditKarte;
import valueObject.ZahlungsstatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ZahlungProcessedEvent {
    private String zahlungId;
    private String bestellungId;
    private BigDecimal gesamtpreis;
    private KreditKarte kreditKarte;
    private ZahlungsstatusEnum zahlungsstatusEnum;
}
