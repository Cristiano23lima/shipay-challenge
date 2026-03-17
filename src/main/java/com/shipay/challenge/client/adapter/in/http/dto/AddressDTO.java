package com.shipay.challenge.client.adapter.in.http.dto;

public record AddressDTO (
        String zipCode,
        String street,
        String neighborhood,
        String city,
        String state
) {}
