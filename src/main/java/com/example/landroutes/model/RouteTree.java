package com.example.landroutes.model;

import java.util.*;

public class RouteTree {
    private final Map<Integer, List<RouteNode>> nodesByLevel;

    public RouteTree(Country originCountry) {
        RouteNode root = new RouteNode(0, originCountry, null);
        nodesByLevel = new HashMap<>();
        nodesByLevel.put(0, Collections.singletonList(root));
    }

    public boolean isCountryAlreadyVisited(String identifier) {
        return getTopNodes().stream().anyMatch(topNode ->
                        topNode.getRoute().stream().anyMatch(identifier::equals));
    }

    public List<RouteNode> getTopNodes() {
        return nodesByLevel.get(nodesByLevel.size() - 1);
    }
}
