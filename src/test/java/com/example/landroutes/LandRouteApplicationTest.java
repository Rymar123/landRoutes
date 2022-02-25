package com.example.landroutes;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class LandRouteApplicationTest {
    private static final String EXPECTED_DATA = """
            {
              "route": ["CZE", "AUT", "ITA"]
            }""";

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    void shouldReturn400IfNoLandRoute() throws Exception {
        this.mockMvc.perform(get("/routing/CZE/JPN"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @Order(2)
    void shouldReturn400IfOriginCountryUnrecognized() throws Exception {
        this.mockMvc.perform(get("/routing/XDD/ITA"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @Order(3)
    void shouldReturn400IfDestinationCountryUnrecognized() throws Exception {
        this.mockMvc.perform(get("/routing/CZE/XDD"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @Order(4)
    void shouldReturnSingleLandRouteForObviousExample() throws Exception {
        this.mockMvc.perform(get("/routing/CZE/ITA"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").value(EXPECTED_DATA));
    }
}
