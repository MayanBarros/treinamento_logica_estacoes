package com.treinamento.rotaestacoes.controller;

import com.treinamento.rotaestacoes.exception.OriginDestinationNotValid;
import com.treinamento.rotaestacoes.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.function.Function;

@Component
public class StationHandler {

    @Autowired
    private RouteService service;

    public Mono<ServerResponse> getMinimumRoute(ServerRequest request) {
        return request.bodyToMono(List.class)
                .flatMap(list -> {
            var result = this.service.getFinalRoute(list);
            return ServerResponse.ok().bodyValue(result);
        })
                .onErrorResume(OriginDestinationNotValid.class, e -> {
                    return ServerResponse.badRequest().bodyValue(e.getMessage());
                })
                .onErrorResume(treatGenericError());
    }

    private Function<Throwable, Mono<ServerResponse>> treatGenericError() {
        return error -> {
            return ServerResponse
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .bodyValue(error.getMessage());
        };
    }
}
