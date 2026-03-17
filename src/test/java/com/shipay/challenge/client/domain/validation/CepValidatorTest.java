package com.shipay.challenge.client.domain.validation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CepValidatorTest {
    @ParameterizedTest
    @ValueSource(strings = {"76800-000", "76800000"})
    void isValid_returnTrue_whenCEPIsValid(String cep){
        Assertions.assertTrue(CepValidator.isValid(cep));
    }

    @ParameterizedTest
    @ValueSource(strings = {"76800-0300", "768000300", "sd23934-234", "dkdfg-sdc", "a2341-432"})
    void isValid_returnFalse_whenCEPIsNotValid(String cep){
        Assertions.assertFalse(CepValidator.isValid(cep));
    }
}
