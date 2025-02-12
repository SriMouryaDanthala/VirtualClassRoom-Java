package com.example.VirtualClassRoom.DAO;

import com.example.VirtualClassRoom.genericDatatypes.ServiceResponse;
import com.example.VirtualClassRoom.interfaces.IServiceResponse;
import com.example.VirtualClassRoom.models.Credentials;
import com.example.VirtualClassRoom.repository.CredentialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CredentialsDAO implements IServiceResponse {
    @Autowired
    private CredentialsRepository credentialsRepository;

    public Credentials getCredentialsForUserName(String userName){
        var result = credentialsRepository.getCredentialsByUsername(userName);
        return result;
    }

    public Credentials getCredentialsForID(UUID ID){
        var result = credentialsRepository.findById(ID);
        return result.isPresent() ? (Credentials) result.get() : null;
    }

    public ServiceResponse<Credentials> saveCredentials(Credentials credentials){
        Exception throwabbleException = null;
        try{
            credentialsRepository.save(credentials);
            return createServiceResponse(credentials,null,"", HttpStatus.CREATED, true);
        }
        catch (Exception e){
            throwabbleException = e;
        }
        return createServiceFailureResponse(
                null,
                throwabbleException,
                throwabbleException.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR
        );

    }
}


