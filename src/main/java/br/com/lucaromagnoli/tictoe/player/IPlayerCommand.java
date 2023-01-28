package br.com.lucaromagnoli.tictoe.player;

import br.com.lucaromagnoli.tictoe.session.UglySessionPlayer;
import org.springframework.web.reactive.socket.WebSocketSession;

public interface IPlayerCommand {
    default Player getPlayer(WebSocketSession session) {
        return UglySessionPlayer.getPlayer(session);
    }
}
