package com.dr.sattlers.bar.employee.waiter.service.exception;

public class WaiterServiceException extends RuntimeException{
    public WaiterServiceException() {
        super();
    }

    public WaiterServiceException(String message) {
        super(message);
    }

    public WaiterServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
