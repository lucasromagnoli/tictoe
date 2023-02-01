package br.com.lucaromagnoli.tictoe.lobby;

import br.com.lucaromagnoli.tictoe.command.external.ICommandPayload;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendLobbyMessagePayload implements ICommandPayload {

    @JsonProperty("message")
    private String message;
}
