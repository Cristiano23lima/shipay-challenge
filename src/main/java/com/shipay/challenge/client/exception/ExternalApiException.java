package com.shipay.challenge.client.exception;


public class ExternalApiException extends RuntimeException {
    public ExternalApiException(final String message) {
        super(message);
    }
}
