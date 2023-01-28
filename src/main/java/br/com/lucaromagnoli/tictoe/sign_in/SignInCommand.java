package br.com.lucaromagnoli.tictoe.sign_in;

import br.com.lucaromagnoli.tictoe.command.AbstractCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SignInCommand extends AbstractCommand<SignInCommandPayload> {

    @Override
    protected void validate(final SignInCommandPayload payload) {
        // TODO: 27/01/2023 Validar Payload
        log.info("Validando o payload.");
    }

    @Override
    protected void doExecute(final SignInCommandPayload payload) {
        log.info("Executando o payload");
    }

    @Override
    public Class<SignInCommandPayload> payloadClass() {
        return SignInCommandPayload.class;
    }
}
