package com.travix.medusa.busyflights.service.suppliers;

import com.travix.medusa.busyflights.configuretion.ConfigProperties;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirRequest;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
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
public class CrazyAirServiceClient implements FlightSuppliers {

    private static final String CRAZY_AIR_PATH = "/crazy-air/flights";

    private final RestClientTemplate restClientTemplate;
    private final ConfigProperties configProperties;
    private final ConversionService conversionService;

    @Override
    public Stream<BusyFlightsResponse> searchFlights(BusyFlightsRequest busyFlightsRequest) {
        CrazyAirRequest crazyAirRequest = conversionService.convert(busyFlightsRequest, CrazyAirRequest.class);
        URI uri = fromUriString(configProperties.getCrazyAirBaseUrl() + CRAZY_AIR_PATH).buildAndExpand().toUri();
        CrazyAirResponse[] response = restClientTemplate.post(uri, crazyAirRequest, CrazyAirResponse[].class);
        log.info("Total number of flights returned from crazy-air {}", response.length);
        return asList(response).stream()
                .map(crazyAirResponses -> conversionService.convert(crazyAirResponses, BusyFlightsResponse.class));
    }
}
