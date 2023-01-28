package br.com.lucaromagnoli.tictoe.command;

public abstract class AbstractCommand<T extends ICommandPayload> {
    protected void validate(final T payload) {
        throw new UnsupportedOperationException();
    }

    protected void doExecute(final T payload) {
        throw new UnsupportedOperationException();
    }

    private T parse(final ICommandPayload commandPayloadAsJson) {
        return this.payloadClass().cast(commandPayloadAsJson);
    }

    public Class<T> payloadClass() {
        throw new UnsupportedOperationException();
    }

    public void execute(final ICommandPayload commandPayload) {
        final T convertedPayload = this.parse(commandPayload);
        this.validate(convertedPayload);
        this.doExecute(convertedPayload);
    }
}
