package com.shipay.challenge.client.adapter.out.gateway.brasilapi.response;

public record CepResponse (
        String cep,
        String state,
        String city,
        String neighborhood,
        String street,
        String service,
        LocationResponse location){
}

record LocationResponse (
        String type,
        CoordinatesResponse coordinates
) {}

record CoordinatesResponse (
        String longitude,
        String latitude
){}