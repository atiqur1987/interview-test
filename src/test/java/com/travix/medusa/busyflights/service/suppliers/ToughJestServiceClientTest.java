package com.travix.medusa.busyflights.service.suppliers;

import com.travix.medusa.busyflights.configuretion.ConfigProperties;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetRequest;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;
import com.travix.medusa.busyflights.rest.RestClientTemplate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.convert.ConversionService;

import java.net.URI;
import java.time.Instant;
import java.util.List;

import static java.time.LocalDate.parse;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class ToughJestServiceClientTest {

    @Mock
    private RestClientTemplate restClientTemplate;
    @Mock
    private ConfigProperties configProperties;
    @Mock
    private ConversionService conversionService;

    private ToughJestServiceClient toughJestServiceClient;

    @BeforeEach
    public void setup() {
        toughJestServiceClient = new ToughJestServiceClient(restClientTemplate, configProperties, conversionService);
    }

    @Test
    public void testSearchFlights() {
        BusyFlightsRequest busyFlightsRequest = new BusyFlightsRequest("aaa", "bbb", parse("2020-02-16"), parse("2020-02-16"), 3);
        ToughJetRequest toughJetRequest = new ToughJetRequest("aaa", "bbb", parse("2020-02-16"), parse("2020-02-16"), 3);
        given(restClientTemplate.post(any(URI.class), eq(toughJetRequest), any(Class.class))).willReturn(expectedToughJetResponse());
        BusyFlightsResponse mockBusyFlightsResponse = mock(BusyFlightsResponse.class);
        given(conversionService.convert(any(), any())).willReturn(toughJetRequest, mockBusyFlightsResponse);

        List<BusyFlightsResponse> result = toughJestServiceClient.searchFlights(busyFlightsRequest).collect(toList());
        assertThat(result.equals(asList(mockBusyFlightsResponse, mockBusyFlightsResponse))).isTrue();
    }

    private ToughJetResponse[] expectedToughJetResponse() {
        Instant instant = Instant.now();
        ToughJetResponse[] response = new ToughJetResponse[2];
        response[0] = new ToughJetResponse("ToughJet", 500.35, 50.20, 30.44, "BDD", "HTR", instant, instant);
        response[1] = new ToughJetResponse("ToughJet", 57.22, 5.97, 4.39, "BDD", "HTR", instant, instant);

        return response;
    }
}