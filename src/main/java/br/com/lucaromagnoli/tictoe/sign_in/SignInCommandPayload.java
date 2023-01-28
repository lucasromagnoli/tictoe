package br.com.lucaromagnoli.tictoe.sign_in;

import br.com.lucaromagnoli.tictoe.command.external.ICommandPayload;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignInCommandPayload implements ICommandPayload {

    @JsonProperty("username")
    private String username;
}
