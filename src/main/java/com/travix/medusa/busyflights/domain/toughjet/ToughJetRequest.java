package com.travix.medusa.busyflights.domain.toughjet;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ToughJetRequest {

    @NotNull
    @Size(min = 3, max = 3, message = "The from IATA code size must be 3 character")
    private String from;
    @NotNull
    @Size(min = 3, max = 3, message = "The destination IATA code size cannot exceed 3 character")
    private String to;
    @NotNull
    private LocalDate outboundDate; //ISO_LOCAL_DATE
    @NotNull
    private LocalDate inboundDate; //ISO_LOCAL_DATE
    @NotNull
    private int numberOfAdults;
}
