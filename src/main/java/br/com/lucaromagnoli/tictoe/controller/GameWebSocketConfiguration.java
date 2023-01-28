package br.com.lucaromagnoli.tictoe.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.reactive.HandlerMapping;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;

import java.util.Map;

import static br.com.lucaromagnoli.tictoe.controller.Constants.WEB_SOCKET_HANDLER_PATH;

@Configuration
@RequiredArgsConstructor
public class GameWebSocketConfiguration {
    @Bean
    public HandlerMapping handlerMapping(GameWebSocketHandler gameWebSocketHandler) {
        return new SimpleUrlHandlerMapping(Map.of(WEB_SOCKET_HANDLER_PATH, gameWebSocketHandler), Ordered.HIGHEST_PRECEDENCE);
    }
}
