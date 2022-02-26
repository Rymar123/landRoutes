package com.example.landroutes.model;

import com.example.landroutes.exception.EmptyTreeLevelException;

import java.util.*;

public class RouteTree {
    private final Map<Integer, List<RouteNode>> nodesByLevel;

    public RouteTree(RouteNode root) {
        nodesByLevel = new HashMap<>();
        nodesByLevel.put(0, Collections.singletonList(root));
    }

    public boolean isCountryNotYetVisited(String identifier) {
        return getTopNodes().stream().noneMatch(topNode ->
                        topNode.getRoute().stream().anyMatch(identifier::equals));
    }

    public List<RouteNode> getTopNodes() {
        return nodesByLevel.get(getTopLevel());
    }

    public void addNodeAtLevel(RouteNode node, int level) {
        nodesByLevel.get(level).add(node);
    }

    public Optional<RouteNode> getTopNodeForIdentifier(String identifier) {
        return getTopNodes().stream()
                .filter(node ->
                        node.getCountry().getIdentifier().equals(identifier))
                .findAny();
    }

    public void addLevel(int level) {
        if(nodesByLevel.get(level - 1).isEmpty()) {
            throw new EmptyTreeLevelException(level - 1);
        }
        nodesByLevel.put(level, new ArrayList<>());
    }

    private int getTopLevel() {
        return nodesByLevel.size() - 1;
    }
}
