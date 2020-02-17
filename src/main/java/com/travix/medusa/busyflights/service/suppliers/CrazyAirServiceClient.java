package com.travix.medusa.busyflights.service.suppliers;

import com.travix.medusa.busyflights.configuretion.Properties;
import com.travix.medusa.busyflights.converter.BusyFlightsRequestConverter;
import com.travix.medusa.busyflights.converter.CrazyAirResponseConverter;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirRequest;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import com.travix.medusa.busyflights.rest.RestClientTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.stream.Stream;

import static org.springframework.web.util.UriComponentsBuilder.fromUriString;

@Service
@RequiredArgsConstructor
public class CrazyAirServiceClient implements FlightSuppliers {

    private static final String CRAZY_AIR_PATH = "/crazy-air/flights";

    private final RestClientTemplate restClientTemplate;
    private final Properties properties;
    private final CrazyAirResponseConverter crazyAirResponseConverter;
    private final BusyFlightsRequestConverter busyFlightsRequestConverter;

    @Override
    public Stream<BusyFlightsResponse> searchFlights(BusyFlightsRequest busyFlightsRequest) {
        CrazyAirRequest crazyAirRequest = busyFlightsRequestConverter.busyFlightsRequestToCrazyAirRequest(busyFlightsRequest);

        URI uri = fromUriString(properties.getCrazyAirBaseUrl() + CRAZY_AIR_PATH).buildAndExpand().toUri();
        CrazyAirResponse[] crazyAirResponses = restClientTemplate.post(uri, crazyAirRequest, CrazyAirResponse[].class);

        return crazyAirResponseConverter.crazyAirResponseToBusyFlightsResponse(crazyAirResponses);
    }
}
