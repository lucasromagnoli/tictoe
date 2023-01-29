package br.com.lucaromagnoli.tictoe.player;

import br.com.lucaromagnoli.tictoe.command.external.AbstractCommand;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Mono;

import java.util.Optional;

public class HelloWorldCommand extends AbstractCommand<HelloWorldPayload> implements IPlayerCommand {
    @Override
    protected void validate(HelloWorldPayload payload, WebSocketSession session) {

    }

    @Override
    protected void doExecute(HelloWorldPayload payload, WebSocketSession session) {
        final var username = Optional.ofNullable(this.getPlayer(session))
                .map(Player::getUsername)
                .orElse(null);
        session.send(Mono.just("Hello World " + username).map(session::textMessage)).subscribe();
    }

    @Override
    public Class<HelloWorldPayload> payloadClass() {
        return HelloWorldPayload.class;
    }
}
