package com.travix.medusa.busyflights.controller.mock.suppliers;

import com.travix.medusa.busyflights.domain.crazyair.CrazyAirRequest;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

import static java.util.Arrays.asList;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class MockCrazyAirController {

    @RequestMapping(value = "/crazy-air/flights", method = POST)
    public List<CrazyAirResponse> getCrazyAirFlights(@Valid @RequestBody CrazyAirRequest request) {

        LocalDateTime localDateTime = LocalDateTime.now();

        List<CrazyAirResponse> response = asList(
                new CrazyAirResponse("CrazyAir", 500.35, "A", "BDD", "AZP", localDateTime, localDateTime),
                new CrazyAirResponse("CrazyAir", 57.22, "A", "BDD", "FNN", localDateTime, localDateTime),
                new CrazyAirResponse("CrazyAir", 420.93, "A", "BDD", "PLZ", localDateTime, localDateTime),
                new CrazyAirResponse("CrazyAir", 76.86, "A", "BDD", "QER", localDateTime, localDateTime));

        return response;
    }
}
