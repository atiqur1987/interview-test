package com.travix.medusa.busyflights.domain.crazyair;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class CrazyAirResponse {

    @NotNull
    private String airline;
    @NotNull
    private double price;
    @NotNull
    private String cabinclass;
    @NotNull
    private String departureAirportCode;
    @NotNull
    private String destinationAirportCode;
    @NotNull
    private LocalDateTime departureDate; //ISO_LOCAL_DATE_TIME
    @NotNull
    private LocalDateTime arrivalDate; //ISO_LOCAL_DATE_TIME
}
