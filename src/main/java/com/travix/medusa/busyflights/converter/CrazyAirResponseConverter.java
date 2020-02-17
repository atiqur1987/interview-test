package com.travix.medusa.busyflights.converter;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static java.math.RoundingMode.HALF_UP;
import static java.util.Arrays.stream;

@Component
public class CrazyAirResponseConverter {

    private static final int DECIMALS_POINT = 2;

    public Stream<BusyFlightsResponse> crazyAirResponseToBusyFlightsResponse(CrazyAirResponse[] crazyAirResponse) {
        return stream(crazyAirResponse)
                .map(this::crazyAirResponseToBusyFlightsResponse);
    }

    private BusyFlightsResponse crazyAirResponseToBusyFlightsResponse(CrazyAirResponse crazyAirResponse) {
        return BusyFlightsResponse.builder()
                .airline(crazyAirResponse.getAirline())
                .fare(new BigDecimal(crazyAirResponse.getPrice()).setScale(DECIMALS_POINT, HALF_UP))
                .departureAirportCode(crazyAirResponse.getDepartureAirportCode())
                .destinationAirportCode(crazyAirResponse.getDestinationAirportCode())
                .departureDate(crazyAirResponse.getDepartureDate())
                .arrivalDate(crazyAirResponse.getArrivalDate())
                .build();
    }

}
