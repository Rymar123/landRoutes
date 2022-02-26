package com.example.landroutes.controller;

import com.example.landroutes.service.LandRouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/routing")
@RequiredArgsConstructor
public class LandRouteController {

    private final LandRouteService landRouteService;

    @GetMapping(value = "{origin}/{destination}", produces = "application/json")
    public List<String> getRoute(@PathVariable String origin, @PathVariable String destination) {
        return landRouteService.getFirstShortestRoute(origin, destination);
    }
}
