package br.com.lucaromagnoli.tictoe.command.internal;

import br.com.lucaromagnoli.tictoe.session.UglyWebSocketSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.socket.WebSocketSession;


@Slf4j
public class RemoveSocketConnectionInternalCommand {

    public static void execute(WebSocketSession session) {
        log.info("A player has disconnected! [{}]", session.getId());
        UglyWebSocketSession.remove(session);
    }
}
