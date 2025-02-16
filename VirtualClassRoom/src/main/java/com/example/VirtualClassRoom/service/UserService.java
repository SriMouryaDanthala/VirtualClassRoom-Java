package com.example.VirtualClassRoom.service;
import com.example.VirtualClassRoom.DAO.UserDAO;
import com.example.VirtualClassRoom.dto.UserDTO;
import com.example.VirtualClassRoom.genericDatatypes.ApiResponse;
import com.example.VirtualClassRoom.genericDatatypes.ServiceResponse;
import com.example.VirtualClassRoom.interfaces.IConverters;
import com.example.VirtualClassRoom.interfaces.IServiceResponse;
import com.example.VirtualClassRoom.models.User;
import org.springframework.stereotype.Component;
import java.util.*;

@Component
public class UserService implements IServiceResponse, IConverters<User,UserDTO> {
    private final UserDAO userDAO;
    private final UserRoleService userRoleService;

    public UserService(UserDAO userDAO, UserRoleService userRoleService) {
        this.userDAO = userDAO;
        this.userRoleService = userRoleService;
    }


    public  UserDTO convertDTOFromEntity(User user) {
        if(user == null) return  null;
        return new UserDTO(
                user.getUserId(),
                user.getUserName(),
                user.getUserFirstName(),
                user.getUserLastName(),
                user.getUserRole().getRoleName()
        );
    }

    public ServiceResponse<UserDTO> createUser(User user) {
        return createNewUser(user);
    }

    public ApiResponse<UserDTO> getUserForUserID(UUID userID){
        return createApiResponse(getUserDetailsByID(userID));
    }

    public ApiResponse<UserDTO> getUserForUserName(String userName) {
        return createApiResponse(getUserDetailsByUserName(userName));
    }


    private ServiceResponse<UserDTO> getUserDetailsByUserName(String userName) {
        ServiceResponse<User> rawUserData = userDAO.getUserByUserName(userName);
        return interChangeServiceResponse(rawUserData, convertDTOFromEntity(rawUserData.getServiceData()));
    }

    private ServiceResponse<UserDTO> getUserDetailsByID(UUID userID){
        ServiceResponse<User> rawUserData = userDAO.getUserByUserID(userID);
        return interChangeServiceResponse(rawUserData, convertDTOFromEntity(rawUserData.getServiceData()));
    }


    private ServiceResponse<UserDTO> createNewUser(User user) {
        // we assume there are no duplications by the time we reach here.
       var result = userDAO.createUser(user);
       return interChangeServiceResponse(result, convertDTOFromEntity(result.getServiceData()));
    }


    protected boolean userExists(String userName) {
        var response = userDAO.getUserByUserName(userName);
        return response.getServiceData()!=null;
    }

    protected boolean userExists(UUID userID) {
        var response = userDAO.getUserByUserID(userID);
        return response.getServiceData()!=null;
    }


    protected boolean isTeacher(UUID userID) {
        var response = userDAO.getUserByUserID(userID);
        var TeacherRole = userRoleService.getUserRoleForRoleName("Teacher");
        if(response.isSuccess()){
            return response.getServiceData().getUserRole().roleId.equals(TeacherRole.getServiceData().getRoleID());
        }
        return false;
    }

    protected User getFullUserReference(UUID userID) throws Exception {
        // call this method only when the userID is present.
        var resp = userDAO.getUserByUserID(userID);
        if(resp.getException() != null){
            throw resp.getException();
        }
        return resp.getServiceData();

    }



}
