package com.example.VirtualClassRoom.DAO;

import com.example.VirtualClassRoom.genericDatatypes.ServiceResponse;
import com.example.VirtualClassRoom.interfaces.IServiceResponse;
import com.example.VirtualClassRoom.models.ClassRoom;
import com.example.VirtualClassRoom.models.User;
import com.example.VirtualClassRoom.repository.ClassRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class ClassRoomDAO implements IServiceResponse {
    @Autowired
    ClassRoomRepository classRoomRepository;

    /*---------------------All public methods---------------------------*/

    public ServiceResponse<ClassRoom> createNewClassRoom(ClassRoom classRoom){
        return createClassRoom(classRoom);
    }

    public ServiceResponse<ClassRoom> getClassRoomForClassRoomID(UUID classRoomID){
        return getClassRoomForId(classRoomID);
    }

    public ServiceResponse<List<ClassRoom>> getClassRooms(){
        return getAllClassRooms();
    }

    public ServiceResponse<ClassRoom> deleteClassRoom(UUID classRoomID){
        return removeClassRoom(classRoomID);
    }

    public ServiceResponse<User> getClassRoomIncharge(UUID classRoomID){
        return getClassRoomInchargeDetails(classRoomID);
    }

    /*---------------------Private Methods-----------------------------*/

    private ServiceResponse<ClassRoom> createClassRoom(ClassRoom classRoom){
        Exception throwableException = null;
        try{
            var savedClassRoom = classRoomRepository.save(classRoom);
            if(savedClassRoom!=null){
                return createServiceResponse(savedClassRoom, null, "", HttpStatus.CREATED,true);
            }
        }
        catch(Exception e){
            throwableException = e;
        }
        return createServiceFailureResponse(
                null,
                throwableException,
                throwableException!=null ? throwableException.getMessage() : "classroom creation/update failed.",
                HttpStatus.INTERNAL_SERVER_ERROR
        );

    }
    
    private ServiceResponse<ClassRoom> getClassRoomForId(UUID classRoomID){
        Exception throwableException = null;
        try{
            var classRoom = classRoomRepository.findById(classRoomID);
            if(classRoom.isPresent()){
                return createServiceResponse(classRoom.get(), null, "", HttpStatus.OK,true);
            }
        }
        catch(Exception e){
            throwableException = e;
        }
        return createServiceFailureResponse(
                null,
                throwableException,
                throwableException!=null ? throwableException.getMessage() : "no classroom with ID - "+classRoomID.toString(),
                throwableException!=null ? HttpStatus.INTERNAL_SERVER_ERROR : HttpStatus.NOT_FOUND
        );
    }
    
    private ServiceResponse<List<ClassRoom>> getAllClassRooms(){
        Exception throwableException = null;
        try{
            var classRooms = (List<ClassRoom>) classRoomRepository.findAll();
            if(classRooms!=null && !classRooms.isEmpty()){
                return createServiceResponse(classRooms, null, "", HttpStatus.OK,true);
            }
        }
        catch(Exception e){
            throwableException = e;
        }
        return createServiceFailureResponse(
                null,
                throwableException,
                throwableException!=null ? throwableException.getMessage() : "There are no classrooms",
                throwableException!=null ? HttpStatus.INTERNAL_SERVER_ERROR : HttpStatus.NO_CONTENT
        );
    }
    
    private ServiceResponse<ClassRoom> removeClassRoom(UUID classRoomID){
        Exception throwableException = null;
        try{
            var classRoom = classRoomRepository.findById(classRoomID);
            if(classRoom.isPresent()){
                classRoomRepository.delete(classRoom.get());
                return createServiceResponse(classRoom.get(), null, "Deleted Successfully", HttpStatus.NO_CONTENT,true);
            }
        }
        catch(Exception e){
            throwableException = e;
        }
        return createServiceFailureResponse(
                null,
                throwableException,
                throwableException!=null ? throwableException.getMessage() : "no classroom with ID - "+classRoomID.toString(),
                throwableException!=null ? HttpStatus.INTERNAL_SERVER_ERROR : HttpStatus.NOT_FOUND
        );

    }
    
    private ServiceResponse<User> getClassRoomInchargeDetails(UUID classRoomID){
        Exception throwableException = null;
        try{
            var classRoom = classRoomRepository.findById(classRoomID);
            if(classRoom.isPresent()){
                return createServiceResponse(classRoom.get().getClassRoomIncharge(), null, "", HttpStatus.OK,true);
            }
        }
        catch(Exception e){
            throwableException = e;
        }
        return createServiceFailureResponse(
                null,
                throwableException,
                throwableException!=null ? throwableException.getMessage() : "no classroom with ID - "+classRoomID.toString(),
                throwableException!=null ? HttpStatus.INTERNAL_SERVER_ERROR : HttpStatus.NOT_FOUND
        );
    }

}
