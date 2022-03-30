package com.treinamento.rotaestacoes.router;

import com.treinamento.rotaestacoes.controller.StationHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
public class StationRouter {
    @Bean
    public RouterFunction<ServerResponse> route(StationHandler handler) {
        return RouterFunctions
                .route(GET("/station")
                        .and(accept(MediaType.APPLICATION_JSON)), handler::getMinimumRoute);
    }
}
