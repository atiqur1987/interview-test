package com.travix.medusa.busyflights.converter;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;
import com.travix.medusa.busyflights.service.PriceCalculatorService;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static java.time.LocalDateTime.ofInstant;
import static java.time.ZoneOffset.UTC;

@Component
public class ToughJetResponseToBusyFlightsResponsesConverter
        extends RegisterConverter<ToughJetResponse, BusyFlightsResponse> {

    private static final String SUPPLIER_NAME = "ToughJet";
    private final PriceCalculatorService priceCalculatorService;

    public ToughJetResponseToBusyFlightsResponsesConverter(ConversionService conversionService,
            PriceCalculatorService priceCalculatorService) {
        super(conversionService);
        this.priceCalculatorService = priceCalculatorService;
    }

    @Override
    public BusyFlightsResponse convert(ToughJetResponse toughJetResponse) {
        return BusyFlightsResponse.builder()
                .airline(toughJetResponse.getCarrier())
                .supplier(SUPPLIER_NAME)
                .fare(calculatePrice(toughJetResponse))
                .departureAirportCode(toughJetResponse.getDepartureAirportName())
                .destinationAirportCode(toughJetResponse.getArrivalAirportName())
                .departureDate(ofInstant(toughJetResponse.getOutboundDateTime(), UTC))
                .arrivalDate(ofInstant(toughJetResponse.getInboundDateTime(), UTC))
                .build();
    }

    private BigDecimal calculatePrice(ToughJetResponse toughJetResponse) {
        return priceCalculatorService.priceWithDiscount(toughJetResponse.getBasePrice(), toughJetResponse.getDiscount(),
                toughJetResponse.getTax());
    }
}
