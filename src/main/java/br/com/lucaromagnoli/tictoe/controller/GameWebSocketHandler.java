package br.com.lucaromagnoli.tictoe.controller;

import br.com.lucaromagnoli.tictoe.command.Commands;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class GameWebSocketHandler implements WebSocketHandler {

    @Override
    public Mono<Void> handle(WebSocketSession session) {
        return session.receive()
                .doOnSubscribe(subscription -> log.info("A player has connected {}", session.getId()))
                .doOnComplete(() -> log.info("A player has disconnected {}", session.getId()))
                .map(WebSocketMessage::getPayloadAsText)
                .doOnNext(this::externalCommand)
                .then()
                ;
    }

    private void externalCommand(String commandAsJson) {
        final var commandDTO = JsonSupport.toObject(commandAsJson, CommandDTO.class);
        Optional.ofNullable(commandDTO)
                .map(CommandDTO::getCommand)
                .map(Commands::getCommand)
                .ifPresent(abstractCommand -> abstractCommand.execute(commandDTO.getPayload()));
    }
}
