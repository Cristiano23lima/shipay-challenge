package com.shipay.challenge.client.domain.validation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CnpjValidatorTest {
    @ParameterizedTest
    @ValueSource(strings = {"46.096.789/0001-27", "82728312000180"})
    void isValid_returnTrue_whenCNPJIsValid(String cnpj){
        Assertions.assertTrue(CnpjValidator.isValid(cnpj));
    }

    @ParameterizedTest
    @ValueSource(strings = {"827283120001802", "827283120001", "8272831200018U", "46.096.789/0001-AB"})
    void isValid_returnFalse_whenCNPJIsNotValid(String cnpj){
        Assertions.assertFalse(CnpjValidator.isValid(cnpj));
    }
}
