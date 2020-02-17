package com.travix.medusa.busyflights.configuretion;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class Properties {

    @Value("${crazy.air.base.url}")
    private String crazyAirBaseUrl;

    @Value("${tough.jet.base.url}")
    private String toughJetBaseUrl;
}
