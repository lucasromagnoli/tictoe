package br.com.lucaromagnoli.tictoe.player;

import lombok.Builder;
import lombok.Getter;
import org.springframework.web.reactive.socket.WebSocketSession;

@Getter
@Builder
public class Player {
    private final String username;
    private final WebSocketSession session;
}
