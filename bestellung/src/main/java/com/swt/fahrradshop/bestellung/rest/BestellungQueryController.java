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
@RequestMapping
public class BestellungQueryController {

    @Autowired
    private QueryGateway queryGateway;
    @GetMapping("/bestellungen")
    public List<BestellungQueryModel> getBestellungen (){
        FindBestellungenQuery qry = new FindBestellungenQuery() ;
        return queryGateway.query(qry, ResponseTypes.multipleInstancesOf(BestellungQueryModel.class)).join();
    }

    @GetMapping("/bestellungen/{bestellungId}")
    public BestellungQueryModel getBestellungById(@PathVariable String bestellungId){
        FindBestellungByIdQuery qry = new FindBestellungByIdQuery(bestellungId);
        return queryGateway.query(qry,BestellungQueryModel.class).join();
    }
}
