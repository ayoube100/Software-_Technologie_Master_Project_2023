package com.swt.fahrradshop.logistik.service;

import com.swt.fahrradshop.logistik.command.CreateLogistikCommand;
import com.swt.fahrradshop.logistik.dto.LogistikDto;
import com.swt.fahrradshop.logistik.entity.Logistik;
import lombok.AllArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;



import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
@AllArgsConstructor
public class LogistikCommandService {
        //CommandGateway for dispatching commands
    private final CommandGateway commandGateway;

    public CompletableFuture<Logistik> createLogistik (LogistikDto logistikDto) {
        return this.commandGateway.send(new CreateLogistikCommand(
                UUID.randomUUID()
        ));

    }
}
