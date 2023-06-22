package com.swt.fahrradshop.logistik.rest;

import com.swt.fahrradshop.logistik.model.LogistikQueryModel;
//import com.swt.fahrradshop.logistik.query.FindBestellungByIdQuery;
// import com.swt.fahrradshop.logistik.query.FindBestellungenQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


//TODO
@RestController
@RequestMapping
public class LogistikQueryController {

    @Autowired
    private QueryGateway queryGateway;
    //with real time update
 //   @GetMapping(value= "/logistik", produces ="text/event-stream")
   // public Flux<LogistikQueryModel> getLogistiken (){
        //FindBestellungenQuery qry = new FindBestellungenQuery() ;
        //return Mono.fromFuture(queryGateway.query(qry, ResponseTypes.multipleInstancesOf(BestellungQueryModel.class)))
        //       .flatMapMany(Flux::fromIterable);
    }


 //   @GetMapping(value= "/logistik/{logistikId}")
   // public Mono<BestellungQueryModel> getBestellungById(@PathVariable String logistikId){
  //      FindBestellungByIdQuery qry = new FindBestellungByIdQuery(logistikId);
   //     return Mono.fromFuture(queryGateway.query(qry,LogistikQueryModel.class));
 //   }
//}
