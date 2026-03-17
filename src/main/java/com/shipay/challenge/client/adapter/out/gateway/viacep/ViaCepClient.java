package com.shipay.challenge.client.adapter.out.gateway.viacep;

import com.shipay.challenge.client.adapter.out.gateway.viacep.response.CepResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class ViaCepClient {
    private final RestClient restClient;

    public ViaCepClient(final @Qualifier("alternativeClient") RestClient restClient){
        this.restClient = restClient;
    }

    public CepResponse getAddressByCep(final String cep) {
        return restClient.get()
                .uri("/ws/{cep}/json", cep)
                .retrieve()
                .body(CepResponse.class);
    }
}
