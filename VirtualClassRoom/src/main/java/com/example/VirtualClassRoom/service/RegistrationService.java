package com.example.VirtualClassRoom.service;

import com.example.VirtualClassRoom.dto.UserDTO;
import com.example.VirtualClassRoom.dto.UserRegistrationDTO;
import com.example.VirtualClassRoom.genericDatatypes.ApiResponse;
import com.example.VirtualClassRoom.genericDatatypes.ServiceResponse;
import com.example.VirtualClassRoom.interfaces.IServiceResponse;
import com.example.VirtualClassRoom.models.User;
import com.example.VirtualClassRoom.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RegistrationService implements IServiceResponse {
    private final UserRoleService roleService;

    private final CredentialsService credentialsService;

    private final UserService userService;

    RegistrationService(UserRoleService roleService, CredentialsService credentialsService, UserService userService){

        this.roleService = roleService;
        this.credentialsService = credentialsService;
        this.userService = userService;
    }

    public ApiResponse<UserDTO> registerUser(UserRegistrationDTO userRegDTO) {
        return createApiResponse(verifyRegisterUser(userRegDTO));
    }


    private ServiceResponse<UserDTO> verifyRegisterUser(UserRegistrationDTO userRegDTO) {
        // check for weather the username is available or not.
        var existingUser = userService.userExists(userRegDTO.getUserName());
        var isValidRole = roleService.isAValidRole(userRegDTO.getUserRole());
        if(!existingUser && isValidRole) {
            var resp = credentialsService.CreateCredentialsForUser(userRegDTO);
            if(resp.isSuccess()) {
                User user = new User(
                        UUID.randomUUID(),
                        userRegDTO.getUserName(),
                        userRegDTO.getUserFirstName(),
                        userRegDTO.getUserLastName(),
                        roleService.getRawRoleForRoleID(userRegDTO.getUserRole()).getServiceData(),
                        resp.getServiceData()
                );
                return userService.createUser(user);
            }
            else {
                return interChangeServiceResponse(resp, new UserDTO());
            }
        }
        return createServiceFailureResponse(new UserDTO()
                ,null,
                !isValidRole ? "Role - "+userRegDTO.getUserRole()+" not exists" :
                        "User already exists with name " + userRegDTO.getUserName(),
                HttpStatus.CONFLICT
        );
    }
}
