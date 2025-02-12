package com.example.VirtualClassRoom.interfaces;

import com.example.VirtualClassRoom.genericDatatypes.ApiResponse;
import com.example.VirtualClassRoom.genericDatatypes.ServiceResponse;
import org.springframework.http.HttpStatus;

import java.lang.reflect.Type;

public interface IServiceResponse {
    default <T> ServiceResponse<T> createServiceResponse (T Data, Exception e, String message, HttpStatus status, boolean success)
    {
        return new ServiceResponse<T>(
                Data,
                status,
                message,
                e,
                success
        );
    }

    default <T> ApiResponse<T> createApiResponse(ServiceResponse<T> serviceResponse)
    {
        return  new ApiResponse<T>().importServiceResp(serviceResponse);
    }


    default <T> ServiceResponse<T> createServiceFailureResponse(T Data, Exception e, String message, HttpStatus status)
    {
        return  new ServiceResponse<T>(
                Data,
                status,
                message,
                e,
                false
        );
    }

    default <T,D> ServiceResponse<D> interChangeServiceResponse(ServiceResponse<T> oldServiceResponse, D newData){
       return createServiceResponse(
                newData,
                oldServiceResponse.getException(),
                oldServiceResponse.getServiceMessage(),
                oldServiceResponse.getServiceStatus(),
                oldServiceResponse.isSuccess()
        );
    }

    //TODO : implement a generic response.

//    default <T> ServiceResponse<T> createGenricServiceResp( T Data, ServiceResponse<T> serviceResponse)
//    {
//        if(Data instanceof Iterable)
//        {
//            Data.
//        }
//    }
}
