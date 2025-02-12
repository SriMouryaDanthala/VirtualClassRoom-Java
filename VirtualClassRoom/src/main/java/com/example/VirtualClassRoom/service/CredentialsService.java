package com.example.VirtualClassRoom.service;

import com.example.VirtualClassRoom.DAO.CredentialsDAO;
import com.example.VirtualClassRoom.dto.UserRegistrationDTO;
import com.example.VirtualClassRoom.genericDatatypes.ServiceResponse;
import com.example.VirtualClassRoom.interfaces.IServiceResponse;
import com.example.VirtualClassRoom.models.Credentials;
import com.example.VirtualClassRoom.repository.CredentialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.UUID;

@Component
public class CredentialsService implements IServiceResponse {
    private final CredentialsDAO credentialsDAO;
    private HashingService hashingService;

    public CredentialsService(CredentialsDAO credentialsDAO) {
        this.credentialsDAO = credentialsDAO;
    }


    public ServiceResponse<Credentials> CreateCredentialsForUser(UserRegistrationDTO userRegDTO) {
        try{
            hashingService = new HashingService();
            var credentials = new Credentials(
                    UUID.randomUUID(),
                    userRegDTO.getUserName(),
                    hashingService.hash(userRegDTO.getUserPassword()),
                    LocalTime.now()
            );
            hashingService = null;
            return CreateCredentialsForUser(credentials);
        }
        catch (Exception e){
            return createServiceFailureResponse(new Credentials(),e,e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    private ServiceResponse<Credentials> CreateCredentialsForUser(Credentials credentials) {
        return credentialsDAO.saveCredentials(credentials);
    }

}
