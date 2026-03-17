package com.shipay.challenge.client.adapter.out.gateway.brasilapi;

import com.shipay.challenge.client.adapter.out.gateway.brasilapi.response.CepResponse;
import com.shipay.challenge.client.adapter.out.gateway.brasilapi.response.CnpjResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class BrasilApiClient {
    private final RestClient restClient;

    public BrasilApiClient(final @Qualifier("principalClient") RestClient restClient){
        this.restClient = restClient;
    }

    public CepResponse getAddressByCep(final String cep) {
        return restClient.get()
                .uri("/api/cep/v2/{cep}", cep)
                .retrieve()
                .body(CepResponse.class);
    }

    public CnpjResponse getAddressByCNPJ(final String cnpj) {
        return restClient.get()
                .uri("/api/cnpj/v1/{cnpj}", cnpj)
                .retrieve()
                .body(CnpjResponse.class);
    }
}

