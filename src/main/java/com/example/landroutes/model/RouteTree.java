package com.example.landroutes.model;

import com.example.landroutes.exception.EmptyTreeLevelException;

import java.util.*;

public class RouteTree {
    private final Map<Integer, List<RouteNode>> nodesByLevel;
    private int topLevel = 0;

    public RouteTree(RouteNode root) {
        nodesByLevel = new HashMap<>();
        nodesByLevel.put(0, Collections.singletonList(root));
    }

    public boolean isCountryNotYetVisited(String identifier) {
        return getTopNodes().stream().noneMatch(topNode ->
                topNode.getRoute().stream().anyMatch(identifier::equals));
    }

    public List<RouteNode> getTopNodes() {
        return nodesByLevel.get(topLevel);
    }

    public void addNode(RouteNode node) {
        nodesByLevel.get(topLevel).add(node);
    }

    public Optional<RouteNode> getTopNodeForIdentifier(String identifier) {
        return getTopNodes().stream()
                .filter(node ->
                        node.getCountry().getIdentifier().equals(identifier))
                .findAny();
    }

    public void addLevel() {
        if (nodesByLevel.get(topLevel).isEmpty()) {
            throw new EmptyTreeLevelException(topLevel);
        }

        topLevel++;
        nodesByLevel.put(topLevel, new ArrayList<>());
    }
}
