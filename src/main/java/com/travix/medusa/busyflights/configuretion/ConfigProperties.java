package com.travix.medusa.busyflights.configuretion;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties
@Component
@Data
public class ConfigProperties {

    private String crazyAirBaseUrl;
    private String toughJetBaseUrl;
}
