package br.com.lucaromagnoli.tictoe.command.internal;

import br.com.lucaromagnoli.tictoe.session.UglyWebSocketSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.socket.WebSocketSession;


@Slf4j
public class SaveSocketConnectionInternalCommand {

    public static void execute(WebSocketSession session) {
        log.info("A player has connected! [{}]", session.getId());
        UglyWebSocketSession.add(session);
    }
}
