package com.shipay.challenge.client.domain.model;

public record Address (
        String zipCode,
        String street,
        String neighborhood,
        String city,
        String state
){
    public boolean equals(Address address){
        return address.city().equalsIgnoreCase(this.city) &&
                address.street().equalsIgnoreCase(this.street) && address.state().equalsIgnoreCase(this.state);
    }
}
