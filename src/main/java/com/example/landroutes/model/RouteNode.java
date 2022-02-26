package com.example.landroutes.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
public class RouteNode {
    private int level;
    private Country country;
    private RouteNode parent;

    public List<String> getRoute() {
        List<String> route = new ArrayList<>();
        addStep(route, this);

        return route;
    }

    private void addStep(List<String> currentRoute, RouteNode node) {
        currentRoute.add(0, node.getCountry().getIdentifier());
        RouteNode parent = node.getParent();

        if(parent != null) {
            addStep(currentRoute, parent);
        }
    }
}
