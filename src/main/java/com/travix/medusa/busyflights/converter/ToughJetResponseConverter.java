package com.travix.medusa.busyflights.converter;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static java.math.BigDecimal.valueOf;
import static java.math.RoundingMode.HALF_UP;
import static java.time.LocalDateTime.ofInstant;
import static java.time.ZoneOffset.UTC;
import static java.util.Arrays.stream;

@Component
public class ToughJetResponseConverter {

    private static final int DECIMALS_POINT = 2;

    public Stream<BusyFlightsResponse> toughJetResponseToBusyFlightsResponses(ToughJetResponse[] toughJetResponses) {
        return stream(toughJetResponses)
                .map(this::toughJetResponseToBusyFlightsResponses);
    }

    private BusyFlightsResponse toughJetResponseToBusyFlightsResponses(ToughJetResponse toughJetResponse) {
        return BusyFlightsResponse.builder()
                .airline(toughJetResponse.getCarrier())
                .fare(priceCalculator(toughJetResponse))
                .departureAirportCode(toughJetResponse.getDepartureAirportName())
                .destinationAirportCode(toughJetResponse.getArrivalAirportName())
                .departureDate(ofInstant(toughJetResponse.getOutboundDateTime(), UTC))
                .arrivalDate(ofInstant(toughJetResponse.getInboundDateTime(), UTC))
                .build();
    }

    private BigDecimal priceCalculator(ToughJetResponse tjr) {
        double finalPrice = tjr.getBasePrice() - (tjr.getBasePrice() * tjr.getDiscount() / 100) + tjr.getTax();
        return valueOf(finalPrice).setScale(DECIMALS_POINT, HALF_UP);

    }
}
