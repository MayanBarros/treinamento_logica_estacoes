package com.treinamento.rotaestacoes.service;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RotaService {

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
        return getRoutesContainsOrigemDestino(getRoute(), origemDestino.get(0), origemDestino.get(1));
    }

    private int menor = Integer.MAX_VALUE;

    public List<String> getRoutesContainsOrigemDestino(List<String> routes, String origem, String destino) {
        HashMap<String, List<String>> map = new HashMap<String,List<String>>();
        HashMap<Integer, String> rotas = new HashMap<Integer, String>();

        for (String route: routes) {
            String[] ro = route.split(";");
            map.put(route, Arrays.asList(ro));
        }

        for (Map.Entry<String, List<String>> str : map.entrySet()) {
            if (str.getValue().contains(origem + ";" + destino)) rotas.put(1, str.getKey());

            if (str.getValue().contains(origem) && str.getValue().contains(destino)) {
                rotas.put(str.getValue().indexOf(destino) - str.getValue().indexOf(origem), str.getKey());
            }
        }

        rotas.keySet().forEach(val -> {
            if (val < menor && val > 0) menor = val;
        });

        var minimumRoute =  List.of(rotas.get(menor).split(";"));
        var minimumRouteReturn =  new ArrayList<String>();
        for (int i = minimumRoute.indexOf(origem); i <= minimumRoute.indexOf(destino); i++) {
            minimumRouteReturn.add(minimumRoute.get(i));
        }
        menor = Integer.MAX_VALUE;
        return minimumRouteReturn;
    }
}
