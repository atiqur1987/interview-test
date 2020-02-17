package com.travix.medusa.busyflights.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.net.URI;

@Component
@RequiredArgsConstructor
public class RestClientTemplate {

    private final RestTemplate restTemplate;

    public @Valid <T> T post(URI uri, Object request, Class<T> responseType) {
        T response = this.restTemplate.postForObject(uri, request, responseType);
        return response;
    }

}
