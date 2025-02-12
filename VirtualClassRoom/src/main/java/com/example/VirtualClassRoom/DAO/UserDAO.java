package com.example.VirtualClassRoom.DAO;

import com.example.VirtualClassRoom.genericDatatypes.ServiceResponse;
import com.example.VirtualClassRoom.interfaces.IServiceResponse;
import com.example.VirtualClassRoom.models.User;
import com.example.VirtualClassRoom.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class UserDAO implements IServiceResponse {

    @Autowired
    private UserRepository userRepository;

    public ServiceResponse<User> getUserByUserName(String userName){
        Exception throwableException = null;
        try{
            var result  = userRepository.findByUserName(userName);
            if(!result.isEmpty()){
                return  createServiceResponse(result.get(0),null,"", HttpStatus.OK, true);
            }
        }
        catch (Exception e){
            throwableException = e;
        }
        return createServiceFailureResponse(null,
                throwableException,
                throwableException!=null ? throwableException.getMessage() : "User not found for username"+" "+userName+".",
                throwableException!=null ? HttpStatus.INTERNAL_SERVER_ERROR : HttpStatus.NOT_FOUND
        );
    }

    public ServiceResponse<User> getUserByUserID(UUID userID){
        Exception throwableException = null;
        try{
            var result  = userRepository.findById(userID);
            if(result.isPresent()){
                return  createServiceResponse(result.get(),null,"", HttpStatus.OK, true);
            }
        }
        catch (Exception e){
            throwableException = e;
        }
        return createServiceFailureResponse(null,
                throwableException,
                throwableException!=null ? throwableException.getMessage() : "User not found for id"+" "+userID.toString()+"",
                throwableException!=null ? HttpStatus.INTERNAL_SERVER_ERROR : HttpStatus.NOT_FOUND
        );
    }

    public ServiceResponse<User> createUser(User user){
        Exception throwableException = null;
        try {
            var res = userRepository.save(user);
            if(res != null){
                return createServiceResponse(res,null ,"",HttpStatus.CREATED, true);
            }
        }
        catch (Exception e){
            throwableException = e;
        }
        return createServiceFailureResponse(null,
                throwableException,
                throwableException!=null ? throwableException.getMessage() : "User Creation failed",
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    public void getUserCredentialsForUserName(String userName){

    }

    public void getUserCredentialsForUserID(UUID userID){

    }

    //TODO : Implement this method, try using the concept of pagination.
    public void getAllUsers(){

    }

}
