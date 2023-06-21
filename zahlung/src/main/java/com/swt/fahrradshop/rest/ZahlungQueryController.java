package com.swt.fahrradshop.rest;

import com.swt.fahrradshop.model.ZahlungQueryModel;
import com.swt.fahrradshop.query.FindZahlungStatusByIdQuery;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping
public class ZahlungQueryController {
    private final QueryGateway queryGateway;

    public ZahlungQueryController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping("/zahlung/{zahlungId}/status")
    public Mono<String> getStatus(@PathVariable String zahlungId){
        FindZahlungStatusByIdQuery qry = new FindZahlungStatusByIdQuery(zahlungId);
        return Mono.fromFuture(queryGateway.query(qry,String.class));
    }
}
