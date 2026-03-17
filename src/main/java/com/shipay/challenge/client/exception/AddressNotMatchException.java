package com.shipay.challenge.client.exception;

public class AddressNotMatchException extends RuntimeException {
    public AddressNotMatchException(final String message) {
        super(message);
    }
}
