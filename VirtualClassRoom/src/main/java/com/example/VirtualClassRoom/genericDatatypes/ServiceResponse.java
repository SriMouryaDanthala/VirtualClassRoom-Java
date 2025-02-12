package com.example.VirtualClassRoom.genericDatatypes;

import org.springframework.http.HttpStatus;

public class ServiceResponse <T>{
    private T  serviceData;
    private HttpStatus serviceStatus;
    private String serviceMessage;
    private Exception exception;
    private boolean success;

    public ServiceResponse() {}

    public ServiceResponse(T serviceData, HttpStatus serviceStatus, String serviceMessage, Exception exception, boolean success) {
        this.serviceData = serviceData;
        this.serviceStatus = serviceStatus;
        this.serviceMessage = serviceMessage;
        this.exception = exception;
        this.success = success;
    }

    public T getServiceData() {
        return serviceData;
    }

    public void setServiceData(T serviceData) {
        this.serviceData = serviceData;
    }

    public HttpStatus getServiceStatus() {
        return serviceStatus;
    }

    public void setServiceStatus(HttpStatus serviceStatus) {
        this.serviceStatus = serviceStatus;
    }

    public String getServiceMessage() {
        return serviceMessage;
    }

    public void setServiceMessage(String serviceMessage) {
        this.serviceMessage = serviceMessage;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
