package br.com.lucaromagnoli.tictoe.command.external;

import org.springframework.web.reactive.socket.WebSocketSession;

public abstract class AbstractCommand<T extends ICommandPayload> {
    protected void validate(final T payload, final WebSocketSession session) {
        throw new UnsupportedOperationException();
    }

    protected void doExecute(final T payload, final WebSocketSession session) {
        throw new UnsupportedOperationException();
    }

    private T parse(final ICommandPayload commandPayloadAsJson) {
        return this.payloadClass().cast(commandPayloadAsJson);
    }

    public Class<T> payloadClass() {
        throw new UnsupportedOperationException();
    }

    public void execute(final ICommandPayload commandPayload, WebSocketSession session) {
        final T convertedPayload = this.parse(commandPayload);
        this.validate(convertedPayload, session);
        this.doExecute(convertedPayload, session);
    }
}
