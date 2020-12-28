package com.travix.medusa.busyflights.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.service.BusyFlightsService;
import com.travix.medusa.busyflights.service.PriceCalculatorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.List;

import static java.time.LocalDateTime.now;
import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(includeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
                classes = PriceCalculatorService.class))
class BusyFlightsControllerTest {

    private final static String URI = "/busy-flights/flights";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BusyFlightsService busyFlightsService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setup() {
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }

    @Test
    void testGetFlights() throws Exception {

        ObjectMapper o = new ObjectMapper();

        List<BusyFlightsResponse> expectedResponse = asList(BusyFlightsResponse.builder()
                .airline("CrazyAir")
                .supplier("Atiqur")
                .fare(new BigDecimal(57.22))
                .departureAirportCode("AAA")
                .destinationAirportCode("BBB")
                .departureDate(now())
                .arrivalDate(now())
                .build());

        given(busyFlightsService.searchFlights(any(BusyFlightsRequest.class))).willReturn(expectedResponse);
        String requestJson = "{\"origin\":\"aaa\",\"destination\":\"bbb\",\"departureDate\":\"2020-02-16\",\"returnDate\":\"2020-02-16\",\"numberOfPassengers\":2}";

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(URI)
                .content(requestJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk())
                .andReturn();

        assertEquals(objectMapper.writeValueAsString(expectedResponse), result.getResponse().getContentAsString());
    }
}