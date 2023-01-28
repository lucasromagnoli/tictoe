package br.com.lucaromagnoli.tictoe.exception;

public class ConvertJsonToObjectException extends RuntimeException {
    public ConvertJsonToObjectException(Throwable cause) {
        super("Error during object creating.", cause);
    }
}
