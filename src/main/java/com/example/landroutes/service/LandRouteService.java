package com.example.landroutes.service;

import com.example.landroutes.exception.NoLandRouteException;
import com.example.landroutes.exception.NoSuchCountryException;
import com.example.landroutes.model.Country;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class LandRouteService {

    private List<Country> countries;

    public List<String> getFirstShortestRoute(String origin, String destination) {
        if (countries == null) {
            init();
        }

        checkForIslands(origin, destination);

        List<String> route = getRoute(origin, destination);

        if (route.isEmpty()) {
            throw new NoLandRouteException(origin, destination);
        }

        return route;
    }

    private List<String> getRoute(String origin, String destination) {
        DefaultMutableTreeNode originNode = new DefaultMutableTreeNode(origin);
        addNeighborNodes(originNode, getCountryForIdentifier(origin));

    }

    private void addNeighborNodes(DefaultMutableTreeNode node, Country country) {
        country.getNeighbors().forEach(neighbor ->
                {
                    //todo dodaj node dla kazdego nowego kraju - przerób isnotbacktracking
                }
        );
    }


    private boolean isNotBacktracking(List<String> route, Country parent, String neighbor) {
        if (parent != null) {
            log.info("Checking if {} is not on current route {} already and its not one of parent's neighbors {}", neighbor, route, parent.getNeighbors());
            return route.stream().noneMatch(neighbor::equals) && parent.getNeighbors().stream().noneMatch(neighbor::equals);
        }
        return false;
    }

    private Country getCountryForIdentifier(String identifier) {
        log.info("Getting country by id: {}", identifier);
        return countries.stream()
                .filter(country -> identifier.equals(country.getIdentifier()))
                .findAny()
                .orElseThrow(() -> new NoSuchCountryException(identifier));
    }

    private void checkForIslands(String origin, String destination) {
        log.info("Checking if provided countries have any neighbors...");

        if (hasNeighbors(getCountryForIdentifier(origin)) || hasNeighbors(getCountryForIdentifier(destination))) {
            throw new NoLandRouteException(origin, destination);
        }
    }

    private boolean hasNeighbors(Country country) {
        return country.getNeighbors().isEmpty();
    }

    private void init() {
        log.info("Parsing source JSON using Jackson...");
        try {
            File resourceFile = new ClassPathResource("countries.json").getFile();
            countries = new ObjectMapper().readValue(resourceFile, new TypeReference<>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}