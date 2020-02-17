package com.travix.medusa.busyflights.controller.mock.suppliers;

import com.travix.medusa.busyflights.domain.toughjet.ToughJetRequest;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.Instant;
import java.util.List;

import static java.util.Arrays.asList;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class MockToughJetController {

    @RequestMapping(value = "/tough-jet/flights", method = POST)
    public List<ToughJetResponse> getToughJetFlights(@Valid @RequestBody ToughJetRequest request) {
        Instant instant = Instant.now();

        List<ToughJetResponse> response = asList(
                new ToughJetResponse("ToughJet", 500.35, 50.20, 30.44, "BDD", "HTR", instant, instant),
                new ToughJetResponse("ToughJet", 57.22, 5.97, 4.39, "BDD", "HTR", instant, instant),
                new ToughJetResponse("ToughJet", 420.93, 47.36, 57.81, "BDD", "HTR", instant, instant),
                new ToughJetResponse("ToughJet", 76.86, 9.74, 7.16, "BDD", "HTR", instant, instant));

        return response;
    }
}
