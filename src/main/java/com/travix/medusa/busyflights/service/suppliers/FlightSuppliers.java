package com.travix.medusa.busyflights.service.suppliers;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;

import java.util.stream.Stream;

public interface FlightSuppliers {

    Stream<BusyFlightsResponse> searchFlights(BusyFlightsRequest busyFlightsRequest);
}
