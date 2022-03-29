package com.treinamento.rotaestacoes.controller;

import com.treinamento.rotaestacoes.service.RotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class EstacaoHandler {

    @Autowired
    private RotaService service;

    public Mono<ServerResponse> calculaRotaMaisCurta(ServerRequest request) {
        return request.bodyToMono(List.class)
                .flatMap(list -> {
            var result = this.service.getFinalRoute(list);
            return ServerResponse.ok().bodyValue(result);
        });
    }
}
