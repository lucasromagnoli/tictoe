package br.com.lucaromagnoli.tictoe.lobby;

import br.com.lucaromagnoli.tictoe.command.external.AbstractCommand;
import br.com.lucaromagnoli.tictoe.command.external.Commands;
import br.com.lucaromagnoli.tictoe.controller.JsonSupport;
import br.com.lucaromagnoli.tictoe.player.Player;
import br.com.lucaromagnoli.tictoe.response.ResponseStatus;
import br.com.lucaromagnoli.tictoe.response.ResponseTemplate;
import br.com.lucaromagnoli.tictoe.session.UglyLobbySession;
import br.com.lucaromagnoli.tictoe.session.UglySessionPlayer;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Mono;

import java.util.function.Predicate;

public class SendLobbyMessageCommand extends AbstractCommand<SendLobbyMessagePayload> {

    @Override
    protected void validate(SendLobbyMessagePayload payload, WebSocketSession session) {

    }

    @Override
    protected void doExecute(SendLobbyMessagePayload payload, WebSocketSession session) {
        UglyLobbySession.get()
                .map(Player::getSession)
                .forEach(sessionOnLobby -> {
                    sessionOnLobby.send(Mono.just(ResponseTemplate.builder()
                                    .command(Commands.SEND_LOBBY_MESSAGE)
                                    .status(ResponseStatus.SUCCESS)
                                    .payload(SendLobbyMessageResponsePayload.builder()
                                            .player(UglySessionPlayer.getPlayer(session).getUsername())
                                            .message(payload.getMessage())
                                            .build())
                                    .build().toDTO())
                            .map(JsonSupport::toJson)
                            .map(sessionOnLobby::textMessage)).subscribe();
                });
    }

    @Override
    public Class<SendLobbyMessagePayload> payloadClass() {
        return SendLobbyMessagePayload.class;
    }
}
