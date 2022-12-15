package com.dr.sattlers.bar.employee.kitchen.service.exception;

public class KitchenServiceException extends RuntimeException{
    public KitchenServiceException() {
        super();
    }

    public KitchenServiceException(String message) {
        super(message);
    }

    public KitchenServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
