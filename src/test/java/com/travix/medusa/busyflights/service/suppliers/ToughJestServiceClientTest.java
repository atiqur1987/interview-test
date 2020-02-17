package com.travix.medusa.busyflights.service.suppliers;

import com.travix.medusa.busyflights.configuretion.Properties;
import com.travix.medusa.busyflights.converter.BusyFlightsRequestConverter;
import com.travix.medusa.busyflights.converter.ToughJetResponseConverter;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetRequest;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;
import com.travix.medusa.busyflights.rest.RestClientTemplate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.net.URI;
import java.time.Instant;
import java.util.stream.Stream;

import static java.time.LocalDate.parse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ToughJestServiceClientTest {

    @Mock
    private RestClientTemplate restClientTemplate;
    @Mock
    private Properties properties;
    @Mock
    private ToughJetResponseConverter toughJetResponseConverter;
    @Mock
    private BusyFlightsRequestConverter busyFlightsRequestConverter;

    private ToughJestServiceClient toughJestServiceClient;

    @BeforeEach
    public void setup() {
        toughJestServiceClient = new ToughJestServiceClient(restClientTemplate, properties, toughJetResponseConverter,
                busyFlightsRequestConverter);
    }

    @Test
    void testSearchFlights() {
        BusyFlightsRequest busyFlightsRequest = new BusyFlightsRequest("aaa", "bbb", parse("2020-02-16"), parse("2020-02-16"), 3);
        ToughJetRequest toughJetRequest = new ToughJetRequest("aaa", "bbb", parse("2020-02-16"), parse("2020-02-16"), 3);
        given(busyFlightsRequestConverter.busyFlightsRequestToToughJetRequest(busyFlightsRequest)).willReturn(toughJetRequest);
        ToughJetResponse[] expectedToughJetResponses = expectedToughJetResponse();
        given(restClientTemplate.post(any(URI.class), any(Object.class), any(Class.class))).willReturn(expectedToughJetResponses);
        given(toughJetResponseConverter.toughJetResponseToBusyFlightsResponses(expectedToughJetResponses)).willReturn(any(Stream.class));

        toughJestServiceClient.searchFlights(busyFlightsRequest);
    }

    private ToughJetResponse[] expectedToughJetResponse() {
        Instant instant = Instant.now();
        ToughJetResponse[] response = new ToughJetResponse[4];
        response[0] = new ToughJetResponse("ToughJet", 500.35, 50.20, 30.44, "BDD", "HTR", instant, instant);
        response[1] = new ToughJetResponse("ToughJet", 57.22, 5.97, 4.39, "BDD", "HTR", instant, instant);
        response[2] = new ToughJetResponse("ToughJet", 420.93, 47.36, 57.81, "BDD", "HTR", instant, instant);
        response[3] = new ToughJetResponse("ToughJet", 76.86, 9.74, 7.16, "BDD", "HTR", instant, instant);

        return response;
    }
}