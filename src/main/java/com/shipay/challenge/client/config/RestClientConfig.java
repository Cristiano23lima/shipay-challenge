package com.shipay.challenge.client.config;

import com.shipay.challenge.client.exception.ExternalApiException;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.util.Timeout;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

import java.time.Duration;

@Configuration
public class RestClientConfig {

    private static final Logger log = LogManager.getLogger(RestClientConfig.class);

    @Value("${api.connect-timeout}")
    private int connectTimeout;

    @Value("${api.read-timeout}")
    private int readTimeout;

    @Bean
    @Qualifier("principalClient")
    public RestClient principalClient(
            @Value("${api.cep.principal}") final String url) {
        return RestClient.builder()
                .baseUrl(url)
                .defaultHeader("Accept", "application/json")
                .defaultHeader("Content-Type", "application/json")
                .requestFactory(buildRequestFactory())
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
                .requestFactory(buildRequestFactory())
                .defaultStatusHandler(
                        HttpStatusCode::isError,
                        (req, res) -> {
                            String body = new String(res.getBody().readAllBytes());
                            throw new ExternalApiException("Return error for request in " + url + ":" + res.getStatusCode() + " - " + body);
                        })
                .build();
    }

    private ClientHttpRequestFactory buildRequestFactory() {
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(Timeout.ofMilliseconds(connectTimeout))
                .setResponseTimeout(Timeout.ofMilliseconds(readTimeout))
                .build();

        CloseableHttpClient httpClient = HttpClients.custom()
                .setDefaultRequestConfig(requestConfig)
                .build();

        return new HttpComponentsClientHttpRequestFactory(httpClient);
    }
}
