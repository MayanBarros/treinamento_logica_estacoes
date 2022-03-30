package com.treinamento.rotaestacoes.service;

import com.treinamento.rotaestacoes.exception.OriginDestinationNotValid;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RouteService {

    private boolean validOriginDestinationRequest(List<String> originDestination, String origin, String destination) {
        return originDestination.size() != 2 ||
                !getStations().contains(origin) ||
                !getStations().contains(destination) ||
                origin.equals(destination);
    }

    public String getStations() {
        return "Sé Paulista Brigadeiro SãoLuis FariaLima Ipiranga Pinheiros BarraFunda AguaRasa";
    }

    public List<String> getRoute() {
        List<String> listRoute = new ArrayList<>();
        listRoute.add("Sé;Paulista;Brigadeiro;SãoLuis;BarraFunda;Pinheiros;FariaLima"); //1
        listRoute.add("Sé;AguaRasa;Ipiranga;BarraFunda;Pinheiros;FariaLima"); //2
        listRoute.add("Sé;AguaRasa;Ipiranga;BarraFunda;SãoLuis;Brigadeiro;Paulista"); //3
        listRoute.add("Sé;Paulista;AguaRasa;Ipiranga;BarraFunda;Pinheiros;FariaLima"); //4
        listRoute.add("Sé;Paulista;AguaRasa;Ipiranga;BarraFunda;SãoLuis;Brigadeiro;Paulista"); //5
        return listRoute;
    }

    public List<String> getFinalRoute(List<String> originDestination) {
        var origin = originDestination.get(0);
        var destination = originDestination.get(1);
        if (validOriginDestinationRequest(originDestination, origin, destination)) {
            throw new OriginDestinationNotValid("Tamanho de Entrada Inválida ou Origem/Destino inválido!");
        }
        return getRoutesThatContainsOriginDestination(getRoute(), origin, destination);
    }

    List<String> minimumRoute = new ArrayList<>();
    private int smaller = Integer.MAX_VALUE;

    public List<String> getRoutesThatContainsOriginDestination(List<String> routes, String origin, String destination) {
        HashMap<String, List<String>> map = new HashMap<>();
        HashMap<Integer, String> routesMap = new HashMap<>();
        List<String> minimumRouteReturn = new ArrayList<>();

        for (String route : routes) {
            String[] routeSplit = route.split(";");
            var indexOrigin = List.of(routeSplit).indexOf(origin);
            var indexDestination = List.of(routeSplit).indexOf(destination);

            if (indexOrigin > indexDestination && indexDestination != -1) {

                for (int i = 0; i < routeSplit.length / 2; i++) {
                    String temp = routeSplit[i];
                    routeSplit[i] = routeSplit[routeSplit.length - 1 - i];
                    routeSplit[routeSplit.length - 1 - i] = temp;
                }
                map.put(String.join(";", routeSplit), Arrays.asList(routeSplit));
            } else {
                map.put(route, Arrays.asList(routeSplit));
            }
        }

        for (Map.Entry<String, List<String>> str : map.entrySet()) {

            if (str.getValue().contains(origin + ";" + destination)) {
                routesMap.put(1, str.getKey());
            }

            if (str.getValue().contains(origin) && str.getValue().contains(destination)) {
                routesMap.put(str.getValue().indexOf(destination) - str.getValue().indexOf(origin), str.getKey());
            }
        }

        routesMap
                .keySet()
                .forEach(val -> {
                    if (val < smaller && val > 0) {
                        smaller = val;
                    }
                });

        minimumRoute = List.of(routesMap.get(smaller).split(";"));

        for (int i = minimumRoute.indexOf(origin); i <= minimumRoute.indexOf(destination); i++) {
            minimumRouteReturn.add(minimumRoute.get(i));
        }
        smaller = Integer.MAX_VALUE;
        return minimumRouteReturn;
    }
}
