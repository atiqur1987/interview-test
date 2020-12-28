package com.travix.medusa.busyflights.controller;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.service.BusyFlightsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class BusyFlightsController {

    private final BusyFlightsService busyFlightsService;

    @PostMapping(value = "/busy-flights/flights")
    public List<BusyFlightsResponse> getFlights(@Valid @RequestBody BusyFlightsRequest request) {
        log.info("Busy-flights endpoint invoked, request body {}", request);
        return busyFlightsService.searchFlights(request);
    }
}
