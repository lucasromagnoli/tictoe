package br.com.lucaromagnoli.tictoe.command;

import br.com.lucaromagnoli.tictoe.sign_in.SignInCommand;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
public enum Commands {
    NOT_MAPPED(null),
    SIGN_IN(new SignInCommand()),
    ;

    @Getter
    private final AbstractCommand<? extends ICommandPayload> command;
    private static final Map<String, Commands> MAP = new HashMap<>();

    static {
        Arrays.stream(values()).forEach(action -> MAP.put(action.name(), action));
    }

    @JsonCreator
    public static Commands getActionByString(String action) {
        return Optional.ofNullable(action)
                .map(String::toUpperCase)
                .map(MAP::get)
                .orElse(NOT_MAPPED);
    }
}
