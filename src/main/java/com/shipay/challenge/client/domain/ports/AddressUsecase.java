package com.shipay.challenge.client.domain.ports;

import com.shipay.challenge.client.domain.model.Address;

public interface AddressUsecase {
    Address getAddress(String cep, String cnpj);
}
