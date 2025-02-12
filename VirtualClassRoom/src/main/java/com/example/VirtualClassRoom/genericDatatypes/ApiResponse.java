package com.example.VirtualClassRoom.genericDatatypes;

import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

public class ApiResponse <T>{
    public boolean success;
    public T data;
    public String message;
    public HttpStatusCode httpStatusCode;


    public <T> ApiResponse<T> createResponse(boolean success,T data, String message, HttpStatusCode statusCode)
    {
        var resp = new ApiResponse<T>();
        resp.success = success;
        resp.data = data;
        resp.message = message;
        resp.httpStatusCode = statusCode;
        return resp;
    }


    public HttpStatusCode getHttpStatusCode() {
        return httpStatusCode;
    }

    public <T> ApiResponse<T> importServiceResp(ServiceResponse<T> serviceResponse)
    {
        return createResponse(
                serviceResponse.isSuccess(),
                serviceResponse.getServiceData(),
                serviceResponse.getServiceMessage(),
                (HttpStatusCode) serviceResponse.getServiceStatus()
        );
    }
}
