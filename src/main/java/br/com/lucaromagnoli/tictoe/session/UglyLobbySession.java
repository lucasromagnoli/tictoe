package br.com.lucaromagnoli.tictoe.session;

import br.com.lucaromagnoli.tictoe.player.Player;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.stream.Stream;

public class UglyLobbySession {

    private UglyLobbySession() {
    }

    private static final Set<Player> PLAYERS_ON_LOBBY_UGLY_SESSION = new CopyOnWriteArraySet<>();

    public static void add(Player player) {
        PLAYERS_ON_LOBBY_UGLY_SESSION.add(player);
    }

    public static Stream<Player> get() {
        return PLAYERS_ON_LOBBY_UGLY_SESSION.stream();
    }
}
