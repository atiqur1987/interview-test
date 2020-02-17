package com.travix.medusa.busyflights.domain.crazyair;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class CrazyAirRequest {

    @NotNull
    @Size(min = 3, max = 3, message = "The origin IATA code size must be 3 character")
    private String origin;
    @NotNull
    @Size(min = 3, max = 3, message = "The destination IATA code size cannot exceed 3 character")
    private String destination;
    @NotNull
    private LocalDate departureDate; //ISO_LOCAL_DATE
    @NotNull
    private LocalDate returnDate;  //ISO_LOCAL_DATE
    @NotNull
    private Integer passengerCount;

}
