package com.shipay.challenge.client.adapter.out.gateway.brasilapi.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CnpjResponse (
        @JsonProperty("cep") String zipCode,
        @JsonProperty("uf") String state,
        @JsonProperty("cnpj") String cnpj,
        @JsonProperty("country") String country,
        @JsonProperty("bairro") String neighborhood,
        @JsonProperty("numero") String number,
        @JsonProperty("municipio") String city,
        @JsonProperty("logradouro") String street,
        @JsonProperty("codigoPais") String countryCode,
        @JsonProperty("complemento") String complement,
        @JsonProperty("codigoMunicipio") String cityCode
) {
}
