package com.travix.medusa.busyflights.service.suppliers;

import com.travix.medusa.busyflights.configuretion.ConfigProperties;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetRequest;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;
import com.travix.medusa.busyflights.rest.RestClientTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static org.springframework.web.util.UriComponentsBuilder.fromUriString;

@Slf4j
@Service
@RequiredArgsConstructor
public class ToughJestServiceClient implements FlightSuppliers {

    private static final String TOUGH_JET_PATH = "/tough-jet/flights";

    private final RestClientTemplate restClientTemplate;
    private final ConfigProperties configProperties;
    private final ConversionService conversionService;

    public Stream<BusyFlightsResponse> searchFlights(BusyFlightsRequest busyFlightsRequest) {
        ToughJetRequest toughJetRequest = conversionService.convert(busyFlightsRequest, ToughJetRequest.class);
        URI uri = fromUriString(configProperties.getToughJetBaseUrl() + TOUGH_JET_PATH).buildAndExpand().toUri();
        ToughJetResponse[] response = restClientTemplate.post(uri, toughJetRequest, ToughJetResponse[].class);
        log.info("Total number of flights returned from tough-jet {}", response.length);
        return asList(response).stream()
                .map(toughJetResponse -> conversionService.convert(toughJetResponse, BusyFlightsResponse.class));
    }
}
