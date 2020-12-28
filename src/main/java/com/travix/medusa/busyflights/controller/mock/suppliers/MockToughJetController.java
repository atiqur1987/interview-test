package com.travix.medusa.busyflights.controller.mock.suppliers;

import com.travix.medusa.busyflights.domain.toughjet.ToughJetRequest;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.Instant;
import java.util.List;

import static java.util.Arrays.asList;

@RestController
public class MockToughJetController {

    @PostMapping(value = "/tough-jet/flights")
    public List<ToughJetResponse> getToughJetFlights(@Valid @RequestBody ToughJetRequest request) {
        Instant instant = Instant.now();

        List<ToughJetResponse> response = asList(
                new ToughJetResponse("Air Arabia", 500.35, 50.20, 30.44, "BDD", "HTR", instant, instant),
                new ToughJetResponse("Air Canada", 57.22, 5.97, 4.39, "BDD", "HTR", instant, instant),
                new ToughJetResponse("Virgin Atlantic", 420.93, 47.36, 57.81, "BDD", "HTR", instant, instant),
                new ToughJetResponse("Royal Brunei", 76.86, 9.74, 7.16, "BDD", "HTR", instant, instant));

        return response;
    }
}
