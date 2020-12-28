package com.travix.medusa.busyflights.converter;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import com.travix.medusa.busyflights.service.PriceCalculatorService;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static java.math.RoundingMode.HALF_UP;

@Component
public class CrazyAirResponseToBusyFlightsResponseConverter
        extends RegisterConverter<CrazyAirResponse, BusyFlightsResponse> {

    private static final String SUPPLIER_NAME = "CrazyAir";
    private final PriceCalculatorService priceCalculatorService;

    public CrazyAirResponseToBusyFlightsResponseConverter(ConversionService conversionService,
            PriceCalculatorService priceCalculatorService) {
        super(conversionService);
        this.priceCalculatorService = priceCalculatorService;
    }

    @Override
    public BusyFlightsResponse convert(CrazyAirResponse crazyAirResponse) {
        return BusyFlightsResponse.builder()
                .airline(crazyAirResponse.getAirline())
                .supplier(SUPPLIER_NAME)
                .fare(priceCalculatorService.basePrice(crazyAirResponse.getPrice()))
                .departureAirportCode(crazyAirResponse.getDepartureAirportCode())
                .destinationAirportCode(crazyAirResponse.getDestinationAirportCode())
                .departureDate(crazyAirResponse.getDepartureDate())
                .arrivalDate(crazyAirResponse.getArrivalDate())
                .build();
    }
}
