package com.treinamento.rotaestacoes.service;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class RotaService {

    private String origem;
    private String destino;

    public List<String> rotaFinal(List<String> origemDestinoList) {
            this.origem = origemDestinoList.get(0);
            this.destino = origemDestinoList.get(1);
            var rota = this.retornaRotaMaisCurta(origem, destino);
            System.out.println(rota);
            return rota;
    }

    public List<String> retornaRotaMaisCurta(String origem, String destino) {
        StringUtils.trimAllWhitespace(origem);
        StringUtils.trimAllWhitespace(destino);
        switch (origem){
            case "BarraFunda":
                if (destino.equalsIgnoreCase("SãoLuis") ||
                    destino.equalsIgnoreCase("Ipiranga") ||
                    destino.equalsIgnoreCase("Pinheiros")) {
                    return List.of(origem, destino);
                } else {
                    if (destino.equalsIgnoreCase("Luz")) return List.of(origem, "São Luis", destino);
                    if (destino.equalsIgnoreCase("Brigadeiro")) return List.of(origem, "Ipiranga", destino);
                    if (destino.equalsIgnoreCase("FariaLima")) return List.of(origem, "Pinheiros", destino);
                    else return List.of(origem, "Ipiranga", "Brigadeiro", destino);
                }
            case "Brigadeiro":
                if (destino.equalsIgnoreCase("Paulista") ||
                    destino.equalsIgnoreCase("Ipiranga") ||
                    destino.equalsIgnoreCase("Sé")) {
                    return List.of(origem, destino);
                } else {
                    if (destino.equalsIgnoreCase("Luz")) return List.of(origem, "Paulista", destino);
                    if (destino.equalsIgnoreCase("BarraFunda")) return List.of(origem, "Ipiranga", destino);
                    if (destino.equalsIgnoreCase("FariaLima")) return List.of(origem, "Sé", destino);
                    else return List.of(origem, "Ipiranga", "Barra Funda", destino);
                }
            case "Paulista":
                if (destino.equalsIgnoreCase("Brigadeiro") ||
                    destino.equalsIgnoreCase("Luz") ||
                    destino.equalsIgnoreCase("Sé")) {
                    return List.of(origem, destino);
                } else {
                    if (destino.equalsIgnoreCase("SãoLuis")) return List.of(origem, "Luz", destino);
                    if (destino.equalsIgnoreCase("Ipiranga")) return List.of(origem, "Brigadeiro", destino);
                    if (destino.equalsIgnoreCase("FariaLima")) return List.of(origem, "Sé", destino);
                    if (destino.equalsIgnoreCase("Pinheiros")) return List.of(origem, "Sé", "Faria Lima", destino);
                    else return List.of(origem, "Luz", "São Luis", destino);
                }
            case "Sé":
                if (destino.equalsIgnoreCase("FariaLima") ||
                    destino.equalsIgnoreCase("Brigadeiro") ||
                    destino.equalsIgnoreCase("Paulista")) {
                    return List.of(origem, destino);
                } else {
                    if (destino.equalsIgnoreCase("Luz")) return List.of(origem, "Paulista", destino);
                    if (destino.equalsIgnoreCase("Ipiranga")) return List.of(origem, "Brigadeiro", destino);
                    if (destino.equalsIgnoreCase("Pinheiros")) return List.of(origem, "Faria Lima", destino);
                    if (destino.equalsIgnoreCase("SãoLuis")) return List.of(origem, "Paulista", "Luz", destino);
                    else return List.of(origem, "Brigadeiro", "Ipiranga", destino);
                }
            case "Luz":
                if (destino.equalsIgnoreCase("SãoLuis") ||
                    destino.equalsIgnoreCase("Paulista")) {
                    return List.of(origem, destino);
                } else {
                    if (destino.equalsIgnoreCase("BarraFunda")) return List.of(origem, "São Luis", destino);
                    if (destino.equalsIgnoreCase("Sé") || destino.equalsIgnoreCase("Brigadeiro")) return List.of(origem, "Paulista", destino);
                    if (destino.equalsIgnoreCase("Ipiranga")) return List.of(origem, "Paulista", "Brigadeiro", destino);
                    if (destino.equalsIgnoreCase("FariaLima")) return List.of(origem, "Paulista", "Sé", destino);
                    else return List.of(origem, "São Luis", "Barra Funda", destino);
                }
            case "SãoLuis":
                if (destino.equalsIgnoreCase("BarraFunda") ||
                    destino.equalsIgnoreCase("Luz")) {
                    return List.of(origem, destino);
                } else {
                    if (destino.equalsIgnoreCase("Paulista")) return List.of(origem, "Luz", destino);
                    if (destino.equalsIgnoreCase("Ipiranga")) return List.of(origem, "Barra Funda", destino);
                    if (destino.equalsIgnoreCase("Brigadeiro") || destino.equalsIgnoreCase("Sé")) return List.of(origem, "Luz", "Paulista", destino);
                    if (destino.equalsIgnoreCase("Pinheiros")) return List.of(origem, "São Luis", "Barra Funda", destino);
                    else return List.of(origem, "Paulista", "Sé", destino);
                }
            case "Ipiranga":
                if (destino.equalsIgnoreCase("BarraFunda") ||
                    destino.equalsIgnoreCase("Brigadeiro")) {
                    return List.of(origem, destino);
                } else {
                    if (destino.equalsIgnoreCase("SãoLuis") || destino.equalsIgnoreCase("Pinheiros")) return List.of(origem, "Barra Funda", destino);
                    if (destino.equalsIgnoreCase("Paulista") || destino.equalsIgnoreCase("Sé")) return List.of(origem, "Brigadeiro", destino);
                    if (destino.equalsIgnoreCase("Luz")) return List.of(origem, "Barra Funda", destino);
                    if (destino.equalsIgnoreCase("FariaLima")) return List.of(origem, "Barra Funda", "Pinheiros", destino);
                    else return List.of(origem, "Barra Funda", "São Luis", destino);
                }
            case "Pinheiros":
                if (destino.equalsIgnoreCase("BarraFunda") ||
                        destino.equalsIgnoreCase("FariaLima")) {
                    return List.of(origem, destino);
                } else {
                    if (destino.equalsIgnoreCase("SãoLuis") || destino.equalsIgnoreCase("Ipiranga")) return List.of(origem, "Barra Funda", destino);
                    if (destino.equalsIgnoreCase("Brigadeiro") || destino.equalsIgnoreCase("Paulista")) return List.of(origem, "Faria Lima", "Sé", destino);
                    if (destino.equalsIgnoreCase("Luz")) return List.of(origem, "Barra Funda", "São Luis", destino);
                    if (destino.equalsIgnoreCase("FariaLima")) return List.of(origem, "Barra Funda", "Pinheiros", destino);
                    else return List.of(origem, "Faria Lima", destino);
                }
            case "FariaLima":
                if (destino.equalsIgnoreCase("Pinheiros") ||
                    destino.equalsIgnoreCase("Sé")) {
                    return List.of(origem, destino);
                } else {
                    if (destino.equalsIgnoreCase("Paulista") || destino.equalsIgnoreCase("Brigadeiro")) return List.of(origem, "Sé", destino);
                    if (destino.equalsIgnoreCase("SãoLuis") || destino.equalsIgnoreCase("Ipiranga")) return List.of(origem, "Pinheiros", "Barra Funda", destino);
                    if (destino.equalsIgnoreCase("Luz")) return List.of(origem, "Sé", "Paulista", destino);
                    else return List.of(origem, "Pinheiros", destino);
                }
            default:
                throw new RuntimeException("Origem e Destino Inválido");
        }
    }
}
