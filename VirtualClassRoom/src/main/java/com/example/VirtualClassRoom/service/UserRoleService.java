package com.example.VirtualClassRoom.service;

import com.example.VirtualClassRoom.DAO.UserRoleDAO;
import com.example.VirtualClassRoom.dto.RoleDTO;
import com.example.VirtualClassRoom.genericDatatypes.ApiResponse;
import com.example.VirtualClassRoom.genericDatatypes.ServiceResponse;
import com.example.VirtualClassRoom.interfaces.IServiceResponse;
import com.example.VirtualClassRoom.models.UserRole;
import com.example.VirtualClassRoom.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class UserRoleService implements IServiceResponse {
    private final UserRoleDAO userRoleDAO;

    public UserRoleService(UserRoleDAO userRoleDAO) {
        this.userRoleDAO = userRoleDAO;
    }

    public ApiResponse<List<RoleDTO>> getAllUserRoles(){
        return createApiResponse(getUserRoles());
    }

    public ServiceResponse<RoleDTO> getUserRoleForRoleId(UUID roleId){
        var roleRes = userRoleDAO.getUserRoleByRoleID(roleId);
        return interChangeServiceResponse(roleRes,
                roleRes.isSuccess() ? createDTOFromEntity(roleRes.getServiceData()) : null
        );
    }

    public ServiceResponse<UserRole> getRawRoleForRoleID(UUID roleID){
        var roleRes = userRoleDAO.getUserRoleByRoleID(roleID);
        return interChangeServiceResponse(roleRes,
                roleRes.isSuccess() ? roleRes.getServiceData() : null
        );
    }

    public ServiceResponse<RoleDTO> getUserRoleForRoleName(String roleName){
        var roleRes = userRoleDAO.getRoleByRoleName(roleName);
        return interChangeServiceResponse(roleRes,
                roleRes.isSuccess() ? createDTOFromEntity(roleRes.getServiceData()) : null
        );
    }

    private ServiceResponse<List<RoleDTO>> getUserRoles(){
        var results = userRoleDAO.getAllUserRoles();
        if(results.isSuccess()){
            List<RoleDTO> roleDTOs = new ArrayList<>();
            for(var role : results.getServiceData()){
                roleDTOs.add(createDTOFromEntity(role));
            }
            return interChangeServiceResponse(results, roleDTOs);
        }
        return interChangeServiceResponse(results, null);
    }

    protected boolean isAValidRole(String roleName){
        var roles = userRoleDAO.getRoleByRoleName(roleName);
        return roles.isSuccess();
    }

    protected boolean isAValidRole(UUID roleID){
        var roles = userRoleDAO.getUserRoleByRoleID(roleID);
        return roles.isSuccess();
    }



    private RoleDTO createDTOFromEntity(UserRole userRole){
        return new RoleDTO(userRole.getRoleId(), userRole.getRoleName());
    }
}

