package com.example.VirtualClassRoom.DAO;

import com.example.VirtualClassRoom.genericDatatypes.ServiceResponse;
import com.example.VirtualClassRoom.interfaces.IServiceResponse;
import com.example.VirtualClassRoom.models.ClassRoom;
import com.example.VirtualClassRoom.models.User;
import com.example.VirtualClassRoom.models.UserClassRoom;
import com.example.VirtualClassRoom.repository.UserClassRoomRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class UserClassRoomDAO implements IServiceResponse {
    private final UserClassRoomRepository userClassRoomRepository;
    private final ClassRoomDAO classRoomDAO;

    public UserClassRoomDAO(UserClassRoomRepository userClassRoomRepository, ClassRoomDAO classRoomDAO) {
        this.userClassRoomRepository = userClassRoomRepository;
        this.classRoomDAO = classRoomDAO;
    }

    public ServiceResponse<List<ClassRoom>> getClassRoomsEnrolledByUserDAO(User user) {
        return this.getClassRoomsEnrolledByUser(user);
    }

    public ServiceResponse<UserClassRoom> enrollUserInClassRoomDAO(User user, ClassRoom classRoom) {
        return this.enrollUserInClassRoom(user, classRoom);
    }

    public ServiceResponse<UserClassRoom> deEnrollUserFromClassRoomDAO(User user, ClassRoom classRoom) {
        return this.deEnrollUserFromClassRoom(user,classRoom);
    }



    private ServiceResponse<List<ClassRoom>> getClassRoomsEnrolledByUser(User user){
        Exception throwableException = null;
        List<ClassRoom> classRoomList = new ArrayList<ClassRoom>();
        try {
            var userClassRoomJoins = userClassRoomRepository.findUserClassRoomByUser(user);
            for (UserClassRoom userClassRoom : userClassRoomJoins) {
                classRoomList.add(classRoomDAO.getClassRoomForClassRoomID(userClassRoom.getClassRoom().getClassRoomId()).getServiceData());
            }
        }
        catch (Exception e) {
            throwableException = e;
        }
        return createServiceResponse(classRoomList
                ,throwableException
                ,throwableException != null ? throwableException.getMessage() : ""
                ,throwableException != null ? HttpStatus.INTERNAL_SERVER_ERROR : HttpStatus.OK
                ,throwableException == null
        );
    }

    private ServiceResponse<UserClassRoom> enrollUserInClassRoom(User user, ClassRoom classRoom){
        Exception throwableException = null;
        UserClassRoom userClassRoom = null;
        try {
            userClassRoom = new UserClassRoom();
            userClassRoom.setUserClassRoomJoinID(UUID.randomUUID());
            userClassRoom.setClassRoom(classRoom);
            userClassRoom.setUser(user);
            userClassRoomRepository.save(userClassRoom);
        }
        catch (Exception e) {
            throwableException = e;
        }
        return createServiceResponse(userClassRoom
                ,throwableException
                ,throwableException != null ? throwableException.getMessage() : "Enrolled Successfully"
                ,throwableException != null ? HttpStatus.INTERNAL_SERVER_ERROR : HttpStatus.CREATED
                ,throwableException == null
        );
    }

    private ServiceResponse<UserClassRoom> deEnrollUserFromClassRoom(User user, ClassRoom classRoom){
        Exception throwableException = null;
        UserClassRoom userClassRoom = null;
        try{
            userClassRoom = userClassRoomRepository.findUserClassRoomByUserAndClassRoom(user, classRoom);
            if(userClassRoom != null ){
                userClassRoomRepository.delete(userClassRoom);
            }
        }
        catch (Exception e) {
            throwableException = e;
        }
        return  createServiceResponse(
                userClassRoom
                ,throwableException
                ,throwableException != null ? throwableException.getMessage() : "Deleted Successfully"
                ,throwableException !=null ? HttpStatus.INTERNAL_SERVER_ERROR : HttpStatus.GONE
                , throwableException == null
        );
    }

}
