package com.shipay.challenge.client.application.usecase;

import com.shipay.challenge.client.domain.model.Address;
import com.shipay.challenge.client.domain.validation.CepValidator;
import com.shipay.challenge.client.domain.validation.CnpjValidator;
import com.shipay.challenge.client.exception.AddressNotMatchException;
import com.shipay.challenge.client.adapter.out.gateway.CepGateway;
import com.shipay.challenge.client.adapter.out.gateway.CnpjGateway;
import com.shipay.challenge.client.exception.FieldInvalidException;
import org.springframework.stereotype.Service;

@Service
public class AddressUsecase implements com.shipay.challenge.client.domain.ports.AddressUsecase {

    private final CepGateway cepGateway;
    private final CnpjGateway cnpjGateway;

    public AddressUsecase(
            final CepGateway cepGateway,
            final CnpjGateway cnpjGateway
    ){
        this.cepGateway = cepGateway;
        this.cnpjGateway = cnpjGateway;
    }

    @Override
    public Address getAddress(String cep, String cnpj) {
        if (!CepValidator.isValid(cep)) {
            throw new FieldInvalidException("CEP invalid");
        }

        if (!CnpjValidator.isValid(cnpj)){
            throw new FieldInvalidException("CNPJ invalid");
        }

        Address addressByCep = this.cepGateway.getAddressByCEP(cep);
        Address addressByCnpj = this.cnpjGateway.getAddressByCNPJ(cnpj);

        if (!addressByCnpj.equals(addressByCep)) {
            throw new AddressNotMatchException("address not found");
        }

        return addressByCep;
    }
}
