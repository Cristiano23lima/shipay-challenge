package com.shipay.challenge.client.domain.validation;

public class CnpjValidator {
    public static boolean isValid(String cnpj) {
        if (cnpj == null){
            return false;
        }

        cnpj = cnpj.replaceAll("[.\\-/]", "").trim();
        if (cnpj.length() != 14) {
            return false;
        }

        if (cnpj.matches("(\\d)\\1{13}")) {
            return false;
        }

        if (!validateDigit(cnpj, 12)){
            return false;
        }

        return validateDigit(cnpj, 13);
    }

    private static boolean validateDigit(String cnpj, int length) {
        int[] weights = { 6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 };
        int offset = weights.length - length;
        int sum = 0;

        for (int i = 0; i < length; i++) {
            sum += Character.getNumericValue(cnpj.charAt(i)) * weights[i + offset];
        }

        int remainder = sum % 11;
        int digit = remainder < 2 ? 0 : 11 - remainder;

        return digit == Character.getNumericValue(cnpj.charAt(length));
    }
}
