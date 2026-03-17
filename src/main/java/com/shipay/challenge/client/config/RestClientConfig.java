package com.shipay.challenge.client.config;

import com.shipay.challenge.client.exception.ExternalApiException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    private static final Logger log = LogManager.getLogger(RestClientConfig.class);

    @Bean
    @Qualifier("principalClient")
    public RestClient principalClient(
            @Value("${api.cep.principal}") final String url) {
        return RestClient.builder()
                .baseUrl(url)
                .defaultHeader("Accept", "application/json")
                .defaultHeader("Content-Type", "application/json")
                .defaultStatusHandler(
                        HttpStatusCode::isError,
                        (req, res) -> {
                            String body = new String(res.getBody().readAllBytes());
                            throw new ExternalApiException("Return error for request in " + url + ":" + res.getStatusCode() + " - " + body);
                        })
                .build();
    }

    @Bean
    @Qualifier("alternativeClient")
    public RestClient alternativeClient(@Value("${api.cep.alternative}") final String url) {
        return RestClient.builder()
                .baseUrl(url)
                .defaultHeader("Accept", "application/json")
                .defaultHeader("Content-Type", "application/json")
                .defaultStatusHandler(
                        HttpStatusCode::isError,
                        (req, res) -> {
                            String body = new String(res.getBody().readAllBytes());
                            throw new ExternalApiException("Return error for request in " + url + ":" + res.getStatusCode() + " - " + body);
                        })
                .build();
    }
}
