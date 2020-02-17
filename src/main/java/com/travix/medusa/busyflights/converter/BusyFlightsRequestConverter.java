package com.travix.medusa.busyflights.converter;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirRequest;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetRequest;
import org.springframework.stereotype.Component;

@Component
public class BusyFlightsRequestConverter {

    public ToughJetRequest busyFlightsRequestToToughJetRequest(BusyFlightsRequest busyFlightsRequest) {
        return new ToughJetRequest(
                busyFlightsRequest.getOrigin(),
                busyFlightsRequest.getDestination(),
                busyFlightsRequest.getDepartureDate(),
                busyFlightsRequest.getReturnDate(),
                busyFlightsRequest.getNumberOfPassengers());
    }

    public CrazyAirRequest busyFlightsRequestToCrazyAirRequest(BusyFlightsRequest busyFlightsRequest) {
        return new CrazyAirRequest(
                busyFlightsRequest.getOrigin(),
                busyFlightsRequest.getDestination(),
                busyFlightsRequest.getDepartureDate(),
                busyFlightsRequest.getReturnDate(),
                busyFlightsRequest.getNumberOfPassengers());
    }
}
