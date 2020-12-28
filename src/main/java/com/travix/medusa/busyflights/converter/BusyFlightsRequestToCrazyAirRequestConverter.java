package com.travix.medusa.busyflights.converter;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirRequest;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

@Component
public class BusyFlightsRequestToCrazyAirRequestConverter extends RegisterConverter<BusyFlightsRequest, CrazyAirRequest>{

    public BusyFlightsRequestToCrazyAirRequestConverter(ConversionService conversionService) {
        super(conversionService);
    }

    @Override
    public CrazyAirRequest convert(BusyFlightsRequest busyFlightsRequest) {
        return new CrazyAirRequest(
                busyFlightsRequest.getOrigin(),
                busyFlightsRequest.getDestination(),
                busyFlightsRequest.getDepartureDate(),
                busyFlightsRequest.getReturnDate(),
                busyFlightsRequest.getNumberOfPassengers());
    }
}
