package com.treinamento.rotaestacoes.service;

import com.treinamento.rotaestacoes.exception.OrigemDestinoNotValid;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RotaService {

    public String getStations() {
        return "Sé Paulista Brigadeiro SãoLuis FariaLima Ipiranga Pinheiro BarraFunda AguaRasa";
    }

    public List<String> getRoute() {
        List<String> listRoute = new ArrayList<String>();
        listRoute.add("Sé;Paulista;Brigadeiro;SãoLuis;BarraFunda;Pinheiros;FariaLima"); //1
        listRoute.add("Sé;AguaRasa;Ipiranga;BarraFunda;Pinheiros;FariaLima"); //2
        listRoute.add("Sé;AguaRasa;Ipiranga;BarraFunda;SãoLuis;Brigadeiro;Paulista"); //3
        listRoute.add("Sé;Paulista;AguaRasa;Ipiranga;BarraFunda;Pinheiros;FariaLima"); //4
        listRoute.add("Sé;Paulista;AguaRasa;Ipiranga;BarraFunda;SãoLuis;Brigadeiro;Paulista"); //5
        return listRoute;
    }

    public List<String> getFinalRoute(List<String> origemDestino) {
        var origem = origemDestino.get(0);
        var destino = origemDestino.get(1);
        if (origemDestino.size() != 2 ||
                !getStations().contains(origem) ||
                !getStations().contains(destino) ||
                origem.equals(destino)) {
            throw new OrigemDestinoNotValid("Tamanho de Entrada Inválida ou Origem/Destino inválido!");
        }
        return getRoutesContainsOrigemDestino(getRoute(), origem, destino);
    }

    List<String> minimumRoute = new ArrayList<>();
    private int smaller = Integer.MAX_VALUE;
    private final int smallerNegative = Integer.MAX_VALUE;

    public List<String> getRoutesContainsOrigemDestino(List<String> routes, String origem, String destino) {
        HashMap<String, List<String>> map = new HashMap<String,List<String>>();
        HashMap<Integer, String> rotas = new HashMap<Integer, String>();

        for (String route: routes) {
            String[] routeSplit = route.split(";");
            map.put(route, Arrays.asList(routeSplit));
        }

        for (Map.Entry<String, List<String>> str : map.entrySet()) {
            if (str.getValue().contains(origem + ";" + destino)) rotas.put(1, str.getKey());
            if (str.getValue().contains(origem) && str.getValue().contains(destino)) {
                rotas.put(str.getValue().indexOf(destino) - str.getValue().indexOf(origem), str.getKey());
            }
        }

        rotas.keySet().forEach(val -> {
            if (val > 0) {
                if (val < smaller) {
                    smaller = val;
                    minimumRoute = List.of(rotas.get(smaller).split(";"));
                }
            } else {
                if ((val * -1) < smallerNegative && (val * -1) > 0) {
                    minimumRoute = List.of(rotas.get(val).split(";"));
                }
            }
        });

        var minimumRouteReturn =  new ArrayList<String>();

        if (minimumRoute.indexOf(destino) < minimumRoute.indexOf(origem)) {
            for (int i = minimumRoute.indexOf(origem); i >= minimumRoute.indexOf(destino); i--) {
                minimumRouteReturn.add(minimumRoute.get(i));
            }
        }
        for (int i = minimumRoute.indexOf(origem); i <= minimumRoute.indexOf(destino); i++) {
            minimumRouteReturn.add(minimumRoute.get(i));
        }
        smaller = Integer.MAX_VALUE;
        return minimumRouteReturn;
    }


}
