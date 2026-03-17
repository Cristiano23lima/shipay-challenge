package com.shipay.challenge.client.domain.model;

import org.springframework.util.ObjectUtils;

public record Address (
        String zipCode,
        String street,
        String neighborhood,
        String city,
        String state
){
    public boolean equals(Address address){
        if (ObjectUtils.isEmpty(address.city()) || ObjectUtils.isEmpty(address.street())
                || ObjectUtils.isEmpty(address.state())){
            return false;
        }

        return address.city().equalsIgnoreCase(this.city) &&
                address.street().equalsIgnoreCase(this.street) && address.state().equalsIgnoreCase(this.state);
    }
}
