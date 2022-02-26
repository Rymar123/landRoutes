package com.example.landroutes.service;

import com.example.landroutes.exception.EmptyTreeLevelException;
import com.example.landroutes.exception.NoLandRouteException;
import com.example.landroutes.exception.NoSuchCountryException;
import com.example.landroutes.model.Country;
import com.example.landroutes.model.LandRouteResponse;
import com.example.landroutes.model.RouteNode;
import com.example.landroutes.model.RouteTree;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LandRouteService {

    private final List<Country> countries;
    private RouteTree routeTree;

    public LandRouteService(CountryDataProvider dataProvider) {
        countries = dataProvider.run();
    }

    public LandRouteResponse getFirstShortestRoute(String origin, String destination) {
        checkForIslands(origin, destination);
        List<String> route = generateRoute(origin, destination);

        if (route.isEmpty()) {
            throw new NoLandRouteException(origin, destination);
        }

        return new LandRouteResponse(route);
    }

    private List<String> generateRoute(String origin, String destination) {
        Country originCountry = getCountryForIdentifier(origin);
        RouteNode root = new RouteNode(0, originCountry, null);
        routeTree = new RouteTree(root);
        Optional<RouteNode> destinationNode = Optional.empty();

        for (int level = 1; level < countries.size(); level++) {
            destinationNode = routeTree.getTopNodeForIdentifier(destination);
            if (destinationNode.isPresent()) {
                break;
            }
            try {
                addNodesByLevel(level);
            } catch (EmptyTreeLevelException e) {
                throw new NoLandRouteException(origin, destination);
            }
        }

        return destinationNode.orElseThrow(() -> new NoLandRouteException(origin, destination)).getRoute();
    }

    private void addNodesByLevel(int level) {
        List<RouteNode> topNodes = routeTree.getTopNodes();
        routeTree.addLevel();
        topNodes.forEach(node ->
                node.getCountry().getNeighbors().forEach(neighbor ->
                        addNodeToLevel(level, node, neighbor)));
    }

    private void addNodeToLevel(int level, RouteNode parentNode, String current) {
        if (routeTree.isCountryNotYetVisited(current)) {
            Country neighborCountry = getCountryForIdentifier(current);
            RouteNode childNode = new RouteNode(level, neighborCountry, parentNode);
            routeTree.addNode(childNode);
        }
    }

    private Country getCountryForIdentifier(String identifier) {
        return countries.stream()
                .filter(country -> identifier.equals(country.getIdentifier()))
                .findAny()
                .orElseThrow(() -> new NoSuchCountryException(identifier));
    }

    private void checkForIslands(String origin, String destination) {
        if (hasNeighbors(getCountryForIdentifier(origin)) || hasNeighbors(getCountryForIdentifier(destination))) {
            throw new NoLandRouteException(origin, destination);
        }
    }

    private boolean hasNeighbors(Country country) {
        return country.getNeighbors().isEmpty();
    }
}