package com.shipay.challenge.client.adapter.out.gateway;

import com.shipay.challenge.client.domain.model.Address;
import com.shipay.challenge.client.adapter.out.gateway.brasilapi.BrasilApiClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CnpjGateway {

    private static final Logger log = LogManager.getLogger(CnpjGateway.class);
    private final BrasilApiClient brasilApiClient;

    public CnpjGateway(
            final BrasilApiClient brasilApiClient
    ){
        this.brasilApiClient = brasilApiClient;
    }

    @Cacheable(value = "cnpj", key = "#cnpj")
    public Address getAddressByCNPJ(final String cnpj) {
        return Optional.ofNullable(this.brasilApiClient.getAddressByCNPJ(cnpj))
                .map(e -> {
                    log.info("address found: {}", e.toString());
                    return new Address(e.zipCode(), e.street(), e.neighborhood(), e.city(), e.state());
                }).orElseThrow();
    }
}
