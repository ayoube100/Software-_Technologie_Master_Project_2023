package com.swt.fahrradshop.bestellung.rest;

import com.swt.fahrradshop.bestellung.model.WarenkorbQueryModel;
import com.swt.fahrradshop.bestellung.query.FindWarenkoerbeQuery;
import com.swt.fahrradshop.bestellung.query.FindWarenkorbByIdQuery;
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
public class WarenkorbQueryController {

    @Autowired
    private QueryGateway queryGateway;
    @GetMapping("/warenkoerbe")
    public List<WarenkorbQueryModel> getWarenkoerbe (){
        FindWarenkoerbeQuery qry = new FindWarenkoerbeQuery();
        return queryGateway.query(qry, ResponseTypes.multipleInstancesOf(WarenkorbQueryModel.class)).join();
    }

    @GetMapping("/warenkoerbe/{warenkorbId}")
    public WarenkorbQueryModel getWarenkorbById(@PathVariable String warenkorbId){
        FindWarenkorbByIdQuery qry = new FindWarenkorbByIdQuery(warenkorbId);
        return  queryGateway.query(qry, WarenkorbQueryModel.class).join();
    }
}
