package com.asmkhalid101.productinventoryapi.exception;

public class InvalidSkuFormatException extends RuntimeException {
    public InvalidSkuFormatException(String message) {
        super(message);
    }
}
