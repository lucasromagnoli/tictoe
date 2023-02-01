package br.com.lucaromagnoli.tictoe.sign_in;

import br.com.lucaromagnoli.tictoe.command.external.AbstractCommand;
import br.com.lucaromagnoli.tictoe.command.external.Commands;
import br.com.lucaromagnoli.tictoe.controller.JsonSupport;
import br.com.lucaromagnoli.tictoe.player.Player;
import br.com.lucaromagnoli.tictoe.response.ResponseStatus;
import br.com.lucaromagnoli.tictoe.response.ResponseTemplate;
import br.com.lucaromagnoli.tictoe.session.UglyLobbySession;
import br.com.lucaromagnoli.tictoe.session.UglySessionPlayer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Mono;

@Slf4j
public class SignInCommand extends AbstractCommand<SignInCommandPayload> {

    @Override
    protected void validate(final SignInCommandPayload payload, WebSocketSession session) {
        // TODO: 27/01/2023 Validar Payload
        log.info("Validando o payload.");
    }

    @Override
    protected void doExecute(final SignInCommandPayload payload, WebSocketSession session) {
        log.info("Executando o payload");
        final var player = Player.builder()
                .username(payload.getUsername())
                .session(session)
                .build();
        UglySessionPlayer.add(player);
        UglyLobbySession.add(player);
        session.send(this.getResponse(payload)
                .map(session::textMessage))
                .subscribe();
    }

    private Mono<String> getResponse(SignInCommandPayload signInCommandPayload) {
        return Mono.just(ResponseTemplate.builder()
                .status(ResponseStatus.SUCCESS)
                .command(Commands.SIGN_IN)
                .payload(signInCommandPayload)
                .build()
                .toDTO())
                .map(JsonSupport::toJson);
    }

    @Override
    public Class<SignInCommandPayload> payloadClass() {
        return SignInCommandPayload.class;
    }
}
