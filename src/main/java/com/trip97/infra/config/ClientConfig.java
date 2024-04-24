package com.trip97.infra.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ClientConfig {

    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
