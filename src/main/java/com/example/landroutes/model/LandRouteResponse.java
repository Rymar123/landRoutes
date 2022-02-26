package com.example.landroutes.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@Getter
public class LandRouteResponse implements Serializable {
    private List<String> route;
}
