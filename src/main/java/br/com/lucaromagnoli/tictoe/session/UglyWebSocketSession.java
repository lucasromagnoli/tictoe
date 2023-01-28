package br.com.lucaromagnoli.tictoe.session;

import org.springframework.web.reactive.socket.WebSocketSession;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.stream.Stream;

public class UglyWebSocketSession {
    private static final Set<WebSocketSession> UGLY_WEB_SOCKET_SESSION = new CopyOnWriteArraySet<>();

    private UglyWebSocketSession() {
    }

    public static void add(WebSocketSession session) {
        UGLY_WEB_SOCKET_SESSION.add(session);
    }

    public static void remove(WebSocketSession session) {
        UGLY_WEB_SOCKET_SESSION.remove(session);
    }

    public static Stream<WebSocketSession> sessions() {
        return UGLY_WEB_SOCKET_SESSION.stream();
    }
}
