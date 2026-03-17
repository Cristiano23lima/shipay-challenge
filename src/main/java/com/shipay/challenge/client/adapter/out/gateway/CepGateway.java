package com.shipay.challenge.client.adapter.out.gateway;

import com.shipay.challenge.client.domain.model.Address;
import com.shipay.challenge.client.adapter.out.gateway.brasilapi.BrasilApiClient;
import com.shipay.challenge.client.adapter.out.gateway.viacep.ViaCepClient;
import io.github.resilience4j.retry.annotation.Retry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CepGateway {
    private static final Logger log = LogManager.getLogger(CepGateway.class);
    private final BrasilApiClient brasilApiClient;
    private final ViaCepClient viaCepClient;

    public CepGateway(
            final BrasilApiClient brasilApiClient,
            final ViaCepClient viaCepClient
    ){
        this.brasilApiClient = brasilApiClient;
        this.viaCepClient = viaCepClient;
    }

    @Retry(name = "cepService", fallbackMethod = "fallbackToViaCep")
    public Address getAddressByCEP(final String cep) {
        return Optional.ofNullable(this.brasilApiClient.getAddressByCep(cep))
                .map(address -> {
                    log.info("address found: {}", address.toString());
                    return new Address(address.cep(), address.street(), address.neighborhood(), address.city(), address.state());
                }).orElseThrow();
    }

    private Address fallbackToViaCep(String cep, Exception e) {
        log.warn("Fallback to viaCep: {}", e.getMessage());
        return Optional.ofNullable(viaCepClient.getAddressByCep(cep))
                .map(el -> {
                    log.info("address found: {}", el.toString());
                    return new Address(el.zipCode(), el.street(), el.neighborhood(), el.city(), el.state());
                }).orElseThrow();
    }
}
