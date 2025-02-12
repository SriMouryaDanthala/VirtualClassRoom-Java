package com.example.VirtualClassRoom.DAO;

import com.example.VirtualClassRoom.genericDatatypes.ServiceResponse;
import com.example.VirtualClassRoom.interfaces.IServiceResponse;
import com.example.VirtualClassRoom.models.UserRole;
import com.example.VirtualClassRoom.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class UserRoleDAO implements IServiceResponse {

    @Autowired
    private UserRoleRepository userRoleRepository;

    public ServiceResponse<UserRole> getRoleByRoleName(String roleName){
        Exception throwableException = null;
        try{
            var rolesList = userRoleRepository.getUserRoleByRoleName(roleName);
            if(!rolesList.isEmpty()){
                return  createServiceResponse(rolesList.get(0), null, "", HttpStatus.OK,true);
            }
        }
        catch (Exception e){
            throwableException = e;
        }
        return createServiceFailureResponse(
                null,
                throwableException,
                throwableException!=null ? throwableException.getMessage() : "no role with name - "+roleName,
                throwableException!=null ? HttpStatus.INTERNAL_SERVER_ERROR : HttpStatus.NOT_FOUND
        );
    }

    public ServiceResponse<UserRole> getUserRoleByRoleID(UUID roleID){
        Exception throwableException = null;
        try{
            var result  = userRoleRepository.findById(roleID);
            if(result.isPresent()){
                return  createServiceResponse(result.get(),null,"", HttpStatus.OK, true);
            }
        }
        catch (Exception e){
            throwableException = e;
        }
        return createServiceFailureResponse(null,
                throwableException,
                throwableException!=null ? throwableException.getMessage() : "Role not found for id"+" "+roleID.toString()+"",
                throwableException!=null ? HttpStatus.INTERNAL_SERVER_ERROR : HttpStatus.NOT_FOUND
        );
    }

    public ServiceResponse<List<UserRole>> getAllUserRoles(){
        Exception throwableException = null;
        try{
            var result  = (List<UserRole>) userRoleRepository.findAll();
            if(!result.isEmpty()){
                return  createServiceResponse(result,null,"", HttpStatus.OK, true);
            }
        }
        catch (Exception e){
            throwableException = e;
        }
        return createServiceFailureResponse(null,
                throwableException,
                throwableException!=null ? throwableException.getMessage() : "Roles not found in the System",
                throwableException!=null ? HttpStatus.INTERNAL_SERVER_ERROR : HttpStatus.NOT_FOUND
        );
    }
}
