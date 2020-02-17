package com.travix.medusa.busyflights.domain.busyflights;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class BusyFlightsResponse {

    @NotNull
    private String airline;
    private String supplier;
    @NotNull
    private BigDecimal fare;
    @NotNull
    @Size(min = 3, max = 3, message = "The departureAirportCode IATA code size must be 3 character")
    private String departureAirportCode;
    @NotNull
    @Size(min = 3, max = 3, message = "The destinationAirportCode IATA code size must be 3 character")
    private String destinationAirportCode;
    @NotNull
    private LocalDateTime departureDate;  //	ISO_DATE_TIME format
    @NotNull
    private LocalDateTime arrivalDate;   //	ISO_DATE_TIME format
}
