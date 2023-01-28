package br.com.lucaromagnoli.tictoe.sign_in;

import br.com.lucaromagnoli.tictoe.command.external.AbstractCommand;
import br.com.lucaromagnoli.tictoe.player.Player;
import br.com.lucaromagnoli.tictoe.session.UglySessionPlayer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class SignInCommand extends AbstractCommand<SignInCommandPayload> {

    @Override
    protected void validate(final SignInCommandPayload payload, WebSocketSession session) {
        // TODO: 27/01/2023 Validar Payload
        log.info("Validando o payload.");
    }

    @Override
    protected void doExecute(final SignInCommandPayload payload, WebSocketSession session) {
        log.info("Executando o payload");
        UglySessionPlayer.add(Player.builder()
                .username(payload.getUsername())
                .session(session)
                .build());
        session.send(Mono.just("Login Sucess").map(session::textMessage)).subscribe();
    }

    @Override
    public Class<SignInCommandPayload> payloadClass() {
        return SignInCommandPayload.class;
    }
}
