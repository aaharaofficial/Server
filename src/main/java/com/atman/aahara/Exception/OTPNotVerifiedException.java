package com.atman.aahara.Exception;

public class OTPNotVerifiedException extends RuntimeException {
    public OTPNotVerifiedException(String message) {
        super(message);
    }
}
