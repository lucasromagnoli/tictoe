package br.com.lucaromagnoli.tictoe.response;

import br.com.lucaromagnoli.tictoe.command.external.Commands;
import br.com.lucaromagnoli.tictoe.controller.JsonSupport;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Getter
@Setter
@Builder
public class ResponseTemplate {
    private final ResponseStatus status;
    private final Commands command;
    private final Object payload;

    public ResponseTemplateDTO toDTO() {
        return ResponseTemplateDTO.builder()
                .status(this.status.name())
                .command(this.command.name())
                .payload(Optional.ofNullable(this.payload)
                        .map(JsonSupport::toJson)
                        .orElse(null))
                .build();
    }

    @Getter
    @Builder
    public static class ResponseTemplateDTO {
        private final String status;
        private final String command;
        private final String payload;
    }
}
