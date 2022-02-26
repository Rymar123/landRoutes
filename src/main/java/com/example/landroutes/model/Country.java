package com.example.landroutes.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Country {
    @JsonProperty("cca3")
    private String identifier;
    @JsonProperty("borders")
    private Set<String> neighbors = new HashSet<>();
}
