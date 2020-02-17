package com.travix.medusa.busyflights.service;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.service.suppliers.FlightSuppliers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toCollection;

@Service
@RequiredArgsConstructor
public class BusyFlightsService {

    private static final String BUSY_FLIGHT_PATH = "/busy-flights/flights";

    private final List<FlightSuppliers> flightSuppliers;

    public List<BusyFlightsResponse> searchFlights(BusyFlightsRequest request) {

        List<BusyFlightsResponse> searchResult = flightSuppliers.stream()
                .flatMap(supplier -> supplier.searchFlights(request))
                .sorted(comparing(BusyFlightsResponse::getFare))
                .collect(toCollection(LinkedList::new));

        return searchResult;
    }
}
