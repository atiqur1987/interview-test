package com.travix.medusa.busyflights.service.suppliers;

import com.travix.medusa.busyflights.configuretion.Properties;
import com.travix.medusa.busyflights.converter.BusyFlightsRequestConverter;
import com.travix.medusa.busyflights.converter.ToughJetResponseConverter;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetRequest;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;
import com.travix.medusa.busyflights.rest.RestClientTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.stream.Stream;

import static org.springframework.web.util.UriComponentsBuilder.fromUriString;

@Service
@RequiredArgsConstructor
public class ToughJestServiceClient implements FlightSuppliers {

    private static final String TOUGH_JET_PATH = "/tough-jet/flights";

    private final RestClientTemplate restClientTemplate;
    private final Properties properties;
    private final ToughJetResponseConverter toughJetResponseConverter;
    private final BusyFlightsRequestConverter busyFlightsRequestConverter;

    public Stream<BusyFlightsResponse> searchFlights(BusyFlightsRequest busyFlightsRequest) {
        ToughJetRequest toughJetRequest = busyFlightsRequestConverter.busyFlightsRequestToToughJetRequest(busyFlightsRequest);

        URI uri = fromUriString(properties.getToughJetBaseUrl() + TOUGH_JET_PATH).buildAndExpand().toUri();
        ToughJetResponse[] toughJetResponse = restClientTemplate.post(uri, toughJetRequest, ToughJetResponse[].class);

        return toughJetResponseConverter.toughJetResponseToBusyFlightsResponses(toughJetResponse);
    }
}
