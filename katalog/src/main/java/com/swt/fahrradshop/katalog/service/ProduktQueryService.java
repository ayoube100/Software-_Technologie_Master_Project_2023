package com.swt.fahrradshop.katalog.service;

import com.swt.fahrradshop.katalog.entity.Produkt;
import com.swt.fahrradshop.katalog.query.FindProduktQuery;
import lombok.AllArgsConstructor;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.messaging.Message;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProduktQueryService {

    private final QueryGateway queryGateway;
    private final EventStore eventStore;

    public CompletableFuture<Produkt> findById(String produktId) {
        return this.queryGateway.query(
                new FindProduktQuery(UUID.fromString(produktId)),
                ResponseTypes.instanceOf(Produkt.class)
        );
    }

    public List<Object> listEventsForAccount(String produktId) {
        return this.eventStore
                .readEvents(produktId.toString())
                .asStream()
                .map(Message::getPayload)
                .collect(Collectors.toList());
    }
}
