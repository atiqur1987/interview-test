package com.travix.medusa.busyflights.controller;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.service.BusyFlightsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequiredArgsConstructor
public class BusyFlightsController {

    private final BusyFlightsService busyFlightsService;

    @RequestMapping(value = "/busy-flights/flights", method = POST)
    public List<BusyFlightsResponse> getFlights(@Valid @RequestBody BusyFlightsRequest request) {
        return busyFlightsService.searchFlights(request);
    }
}
