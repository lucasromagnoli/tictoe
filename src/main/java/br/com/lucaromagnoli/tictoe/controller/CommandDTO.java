package br.com.lucaromagnoli.tictoe.controller;

import br.com.lucaromagnoli.tictoe.command.external.AbstractCommand;
import br.com.lucaromagnoli.tictoe.command.external.Commands;
import br.com.lucaromagnoli.tictoe.command.external.ICommandPayload;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.io.Serializable;
import java.util.Optional;
import java.util.function.Predicate;

import static br.com.lucaromagnoli.tictoe.controller.Constants.COMMAND_DTO_COMMAND_JSON_PROPERTY;
import static br.com.lucaromagnoli.tictoe.controller.Constants.COMMAND_DTO_PAYLOAD_JSON_PROPERTY;
import static br.com.lucaromagnoli.tictoe.controller.JsonSupport.toObject;

@Setter
@Getter
@Builder
@JsonDeserialize(using = CommandDTO.CommandDTODeserializer.class)
public class CommandDTO implements Serializable {
    private Commands command;

    private ICommandPayload payload;

    static class CommandDTODeserializer extends StdDeserializer<CommandDTO> {

        public CommandDTODeserializer() {
            this(null);
        }

        public CommandDTODeserializer(Class<?> vc) {
            super(vc);
        }

        @Override
        public CommandDTO deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {

            final var productNode = jsonParser.getCodec().readTree(jsonParser);

            final var action = Optional.ofNullable(productNode.get(COMMAND_DTO_COMMAND_JSON_PROPERTY))
                    .map(TreeNode::traverse)
                    .map(actionJsonParser -> toObject(actionJsonParser, Commands.class))
                    .orElse(null);

            final var payloadClass = Optional.ofNullable(action)
                    .filter(Predicate.not(Commands.NOT_MAPPED::equals))
                    .map(Commands::getCommand)
                    .map(AbstractCommand::payloadClass)
                    .orElse(null);

            final var payload = Optional.ofNullable(payloadClass)
                    .map(ignored -> productNode.get(COMMAND_DTO_PAYLOAD_JSON_PROPERTY))
                    .map(TreeNode::traverse)
                    .map(payloadJsonparser -> toObject(payloadJsonparser, payloadClass))
                    .orElse(null);

            return CommandDTO.builder()
                    .command(action)
                    .payload(payload)
                    .build();
        }
    }
}
