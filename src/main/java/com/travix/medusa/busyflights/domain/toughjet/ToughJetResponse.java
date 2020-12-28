package com.travix.medusa.busyflights.domain.toughjet;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;

@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ToughJetResponse {

    @NotNull
    private String carrier;
    @NotNull
    private double basePrice;
    @NotNull
    private double tax;
    @NotNull
    private double discount;
    @NotNull
    @Size(min = 3, max = 3, message = "The from IATA code size must be 3 character")
    private String departureAirportName;
    @NotNull
    @Size(min = 3, max = 3, message = "The from IATA code size must be 3 character")
    private String arrivalAirportName;
    @NotNull
    private Instant outboundDateTime;    //ISO_INSTANT
    @NotNull
    private Instant inboundDateTime;     //ISO_INSTANT
}
