package com.example.VirtualClassRoom.service;
import com.example.VirtualClassRoom.DAO.UserClassRoomDAO;
import com.example.VirtualClassRoom.dto.ClassRoomDTO;
import com.example.VirtualClassRoom.dto.UserClassRoomDTO;
import com.example.VirtualClassRoom.genericDatatypes.ApiResponse;
import com.example.VirtualClassRoom.genericDatatypes.ServiceResponse;
import com.example.VirtualClassRoom.interfaces.IServiceResponse;
import com.example.VirtualClassRoom.models.ClassRoom;
import com.example.VirtualClassRoom.models.User;
import com.example.VirtualClassRoom.models.UserClassRoom;
import jakarta.persistence.EntityManager;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class UserClassRoomService implements IServiceResponse {
    private final ClassRoomService classRoomService;
    private final UserClassRoomDAO userClassRoomDAO;
    private final UserService userService;
    private final EntityManager entityManager;

    public UserClassRoomService(ClassRoomService classRoomService, UserClassRoomDAO userClassRoomDAO, UserService userService, EntityManager entityManager) {
        this.classRoomService = classRoomService;
        this.userClassRoomDAO = userClassRoomDAO;
        this.userService = userService;
        this.entityManager = entityManager;
    }

    public ApiResponse<UserClassRoomDTO> enrollUserInClassRoom(UserClassRoomDTO userClassRoomDTO) {
        return createApiResponse(enrollUserInClassRoom(userClassRoomDTO.getUserID(), userClassRoomDTO.getClassRoomID()));
    }

    public ApiResponse<List<ClassRoomDTO>> retrieveClassRoomsEnrolledByUser(UUID userID){
        return createApiResponse(getClassRoomsEnrolledByUser(userID));
    }

    public ApiResponse<UserClassRoomDTO> removeUserFromClassRoom(UserClassRoomDTO userClassRoomDTO) {
        return createApiResponse(deEnrollUserInClassRoom(userClassRoomDTO.getUserID(), userClassRoomDTO.getClassRoomID()));
    }

    private ServiceResponse<UserClassRoomDTO> enrollUserInClassRoom(UUID UserID, UUID ClassRoomID){

        var validUser  = userService.userExists(UserID);
        var validClassRoom = classRoomService.classRoomExists(ClassRoomID);
        if(validUser && validClassRoom){
            var response = userClassRoomDAO.enrollUserInClassRoomDAO(
                    entityManager.getReference(User.class,UserID)
                    ,entityManager.getReference(ClassRoom.class,ClassRoomID)
            );
            return interChangeServiceResponse(response,createDTOFromEntity(response.getServiceData()));
        }
        return createServiceResponse(
                null
                ,null
                ,!validClassRoom ? "ClassRoom is Not Valid - "+ClassRoomID.toString() : "not a valid user - "+UserID.toString()
                ,HttpStatus.NOT_FOUND
                ,false
        );
    }

    private ServiceResponse<List<ClassRoomDTO>>getClassRoomsEnrolledByUser(UUID userID){
        var validUser = userService.userExists(userID);
        if(validUser){
            var response = userClassRoomDAO.getClassRoomsEnrolledByUserDAO(entityManager.getReference(User.class,userID));
            List<ClassRoomDTO> classRoomDTOS = new ArrayList<>();
            for(ClassRoom classRoom : response.getServiceData()){
                classRoomDTOS.add(classRoomService.createDTOFromEntity(classRoom));
            }
            return interChangeServiceResponse(response,classRoomDTOS);
        }
        return createServiceResponse(
                null,
                null,
                "invalid user  - "+userID.toString(),
                HttpStatus.NOT_FOUND,
                false
        );
    }

    private ServiceResponse<UserClassRoomDTO> deEnrollUserInClassRoom(UUID UserID, UUID ClassRoomID){
        var validUser  = userService.userExists(UserID);
        var validClassRoom = classRoomService.classRoomExists(ClassRoomID);
        if(validUser && validClassRoom){
            var response = userClassRoomDAO.deEnrollUserFromClassRoomDAO(entityManager.getReference(User.class,UserID),entityManager.getReference(ClassRoom.class,ClassRoomID));
            return interChangeServiceResponse(response,createDTOFromEntity(response.getServiceData()));
        }
        return createServiceResponse(
                null,
                null,
                !validUser ? "Not a Valid User - "+UserID.toString() : "Not a Valid ClassRoom - "+ClassRoomID.toString(),
                HttpStatus.NOT_FOUND,
                false
        );
    }

    private UserClassRoomDTO createDTOFromEntity(UserClassRoom entity){
        if(entity == null) return null;
        var dto = new UserClassRoomDTO();
        dto.setUserClassRoomID(entity.getUserClassRoomJoinID());
        dto.setUserID(entity.getUser().getUserId());
        dto.setClassRoomID(entity.getClassRoom().getClassRoomId());
        return dto;
    }
}
