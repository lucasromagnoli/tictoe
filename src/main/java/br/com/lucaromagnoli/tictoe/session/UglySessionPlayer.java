package br.com.lucaromagnoli.tictoe.session;

import br.com.lucaromagnoli.tictoe.player.Player;
import org.springframework.web.reactive.socket.WebSocketSession;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

public class UglySessionPlayer {

    private UglySessionPlayer() {
    }

    private static final Set<Player> PLAYERS_UGLY_SESSION = new CopyOnWriteArraySet<>();
    private static final Map<WebSocketSession, Player> SESSION_X_PLAYERS_UGLY_SESSION = new ConcurrentHashMap<>();

    public static void add(Player player) {
        PLAYERS_UGLY_SESSION.add(player);
        SESSION_X_PLAYERS_UGLY_SESSION.put(player.getSession(), player);
    }

    public static void remove(Player player) {
        PLAYERS_UGLY_SESSION.remove(player);
        SESSION_X_PLAYERS_UGLY_SESSION.remove(player.getSession());
    }

    public static Player getPlayer(WebSocketSession session) {
        return SESSION_X_PLAYERS_UGLY_SESSION.get(session);
    }
}
