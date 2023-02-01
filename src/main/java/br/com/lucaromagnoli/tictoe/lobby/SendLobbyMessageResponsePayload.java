package br.com.lucaromagnoli.tictoe.lobby;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SendLobbyMessageResponsePayload {

    @JsonProperty("player")
    private String player;
    @JsonProperty("message")
    private String message;
}
