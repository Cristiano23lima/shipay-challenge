package com.shipay.challenge.client.domain.validation;

public class CepValidator {
    public static boolean isValid(String cep) {
        if (cep == null) return false;

        cep = cep.replaceAll("[\\-]", "").trim();

        return cep.matches("\\d{8}");
    }
}
