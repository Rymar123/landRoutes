package com.example.landroutes.service;

import com.example.landroutes.model.Country;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Service
public class CountryDataProvider {
    public List<Country> run() {
        try {
            File resourceFile = new ClassPathResource("countries.json").getFile();
            return new ObjectMapper().readValue(resourceFile, new TypeReference<>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}
