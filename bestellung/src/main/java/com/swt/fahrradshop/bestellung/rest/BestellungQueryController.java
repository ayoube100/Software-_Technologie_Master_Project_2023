package com.swt.fahrradshop.bestellung.rest;

import com.swt.fahrradshop.bestellung.model.BestellungQueryModel;
import com.swt.fahrradshop.bestellung.query.FindBestellungByIdQuery;
import com.swt.fahrradshop.bestellung.query.FindBestellungenQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bestellungen")
public class BestellungQueryController {

    @Autowired
    private QueryGateway queryGateway;
    @GetMapping
    public List<BestellungQueryModel> getBestellungen (){
        FindBestellungenQuery qry = new FindBestellungenQuery() ;
        List<BestellungQueryModel> bestellungen = queryGateway.query(qry, ResponseTypes.multipleInstancesOf(BestellungQueryModel.class)).join();
        return bestellungen;
    }

    @GetMapping("/{bestellungToBeFoundId}")
    public BestellungQueryModel getBestellungById(@PathVariable String bestellungToBeFoundId){
        FindBestellungByIdQuery qry = new FindBestellungByIdQuery(bestellungToBeFoundId);
        BestellungQueryModel bestellungToBeFound = queryGateway.query(qry,BestellungQueryModel.class).join();
        return bestellungToBeFound;
    }
}
