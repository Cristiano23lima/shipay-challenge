package com.shipay.challenge.client.application.usecase;


import com.shipay.challenge.client.adapter.out.gateway.CepGateway;
import com.shipay.challenge.client.adapter.out.gateway.CnpjGateway;
import com.shipay.challenge.client.domain.model.Address;
import com.shipay.challenge.client.exception.AddressNotMatchException;
import com.shipay.challenge.client.exception.FieldInvalidException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AddressUsecaseTest {

    @InjectMocks
    private AddressUsecase addressUsecase;

    @Mock
    private CepGateway cepGateway;

    @Mock
    private CnpjGateway cnpjGateway;

    @Test
    void getAddress_returnAddress_whenAddressByCepIsEqualAddressByCNPJ(){
        String cep = "62900000";
        String cnpj = "00924432000199";

        Address expectedAddress = new Address("62900000", "Rua X", "Bairro Y", "Russas", "CE");

        Mockito.when(cepGateway.getAddressByCEP(cep)).thenReturn(expectedAddress);
        Mockito.when(cnpjGateway.getAddressByCNPJ(cnpj)).thenReturn(expectedAddress);

        Address result = addressUsecase.getAddress(cep, cnpj);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(expectedAddress.city(), result.city());
        Assertions.assertEquals(expectedAddress.street(), result.street());
        Assertions.assertEquals(expectedAddress.state(), result.state());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "1231723", "12313", "1203910232", "123345123", "sakdn823"})
    void getAddress_throwFieldInvalidException_whenCEPIsInvalid(String cep){
        String cnpj = "00924432000199";
        Assertions.assertThrows(FieldInvalidException.class, () -> addressUsecase.getAddress(cep, cnpj));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "1231723", "12313", "1203910232", "123345123", "sdck2382837"})
    void getAddress_throwFieldInvalidException_whenCNPJIsInvalid(String cnpj){
        String cep = "62900000";
        Assertions.assertThrows(FieldInvalidException.class, () -> addressUsecase.getAddress(cep, cnpj));
    }

    @Test
    void getAddress_throwAddressNotMatchException_whenAddressByCEPIsNotEqualAddressByCNPJ(){
        String cep = "62900000";
        String cnpj = "00924432000199";

        Address expectedAddressByCep = new Address("62900000", "Rua X", "Bairro Y", "Russas", "CE");
        Address expectedAddressByCNPJ = new Address("62900000", "Rua Y", "Bairro X", "Russas", "CE");

        Mockito.when(cepGateway.getAddressByCEP(cep)).thenReturn(expectedAddressByCep);
        Mockito.when(cnpjGateway.getAddressByCNPJ(cnpj)).thenReturn(expectedAddressByCNPJ);

        Assertions.assertThrows(AddressNotMatchException.class, () -> addressUsecase.getAddress(cep, cnpj));
    }
}
