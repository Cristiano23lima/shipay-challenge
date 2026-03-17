package com.shipay.challenge.client.adapter.out.gateway.viacep.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CepResponse (
        @JsonProperty("cep") String zipCode,
        @JsonProperty("logradouro") String street,
        @JsonProperty("complemento") String complement,
        @JsonProperty("bairro") String neighborhood,
        @JsonProperty("localidade") String city,
        @JsonProperty("uf") String state,
        @JsonProperty("ibge") String ibgeCode
        ){
}