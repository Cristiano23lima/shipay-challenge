package com.shipay.challenge.client.domain.model;

import org.springframework.util.ObjectUtils;

import java.util.Objects;
import java.util.Optional;

public record Address (
        String zipCode,
        String street,
        String neighborhood,
        String city,
        String state
){
    public boolean equals(Address address){
        return Objects.equals(this.city.toLowerCase(),
                Optional.ofNullable(address.city()).map(String::toLowerCase).orElse(null)) &&
                Objects.equals(this.street.toLowerCase(),
                        Optional.ofNullable(address.street()).map(String::toLowerCase).orElse(null)) &&
                Objects.equals(this.state.toLowerCase(),
                        Optional.ofNullable(address.state()).map(String::toLowerCase).orElse(null));
    }
}
