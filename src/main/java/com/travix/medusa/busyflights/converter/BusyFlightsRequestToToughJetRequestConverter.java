package com.travix.medusa.busyflights.converter;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirRequest;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

@Component
public class BusyFlightsRequestToToughJetRequestConverter extends RegisterConverter<BusyFlightsRequest, ToughJetRequest>{

    public BusyFlightsRequestToToughJetRequestConverter(ConversionService conversionService) {
        super(conversionService);
    }

    @Override
    public ToughJetRequest convert(BusyFlightsRequest busyFlightsRequest) {
        return new ToughJetRequest(
                busyFlightsRequest.getOrigin(),
                busyFlightsRequest.getDestination(),
                busyFlightsRequest.getDepartureDate(),
                busyFlightsRequest.getReturnDate(),
                busyFlightsRequest.getNumberOfPassengers());
    }
}
