package br.com.lucaromagnoli.tictoe.sign_in;

import br.com.lucaromagnoli.tictoe.command.ICommandPayload;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignInCommandPayload implements ICommandPayload {
    private String name;
}
