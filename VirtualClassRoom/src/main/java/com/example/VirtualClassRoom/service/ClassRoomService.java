package com.example.VirtualClassRoom.service;

import com.example.VirtualClassRoom.DAO.ClassRoomDAO;
import com.example.VirtualClassRoom.dto.ClassRoomDTO;
import com.example.VirtualClassRoom.dto.ClassRoomRegistrationDTO;
import com.example.VirtualClassRoom.genericDatatypes.ApiResponse;
import com.example.VirtualClassRoom.genericDatatypes.ServiceResponse;
import com.example.VirtualClassRoom.interfaces.IServiceResponse;
import com.example.VirtualClassRoom.models.ClassRoom;
import com.example.VirtualClassRoom.models.User;
import com.example.VirtualClassRoom.repository.ClassRoomRepository;
import jakarta.persistence.EntityManager;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Component
public class ClassRoomService implements IServiceResponse {

    private final ClassRoomRepository classRoomRepository;
    private final EntityManager entityManager;
    private final UserService userService;
    private final ClassRoomDAO classRoomDAO;

    public ClassRoomService(ClassRoomRepository classRoomRepository, EntityManager entityManager, UserService userService, ClassRoomDAO classRoomDAO) {
        this.classRoomRepository = classRoomRepository;
        this.entityManager = entityManager;
        this.userService = userService;
        this.classRoomDAO = classRoomDAO;
    }


    public ApiResponse<ClassRoomDTO> createClassRoom(ClassRoomRegistrationDTO classRoomDTO){
        return createApiResponse(this.registerClassRoom(classRoomDTO));
    }

    public ApiResponse<ClassRoomDTO> getClassRoom(UUID classRoomID){
        return createApiResponse(this.getClassRoomByClassRoomID(classRoomID));
    }


    public ApiResponse<ClassRoomDTO> updateClassRoom(ClassRoomDTO classRoomDTO){
        return createApiResponse(this.modifyClassRoomDetails(classRoomDTO));
    }

    public ApiResponse<ClassRoomDTO> removeClassRoom(UUID classRoomID){
        return createApiResponse(this.deleteClassRoom(classRoomID));
    }


    private ServiceResponse<ClassRoomDTO> registerClassRoom(ClassRoomRegistrationDTO classRoomDTO){
        boolean isValidUser = userService.userExists(classRoomDTO.getClassRoomInchargeID());
        boolean doesClassRoomExist = classRoomExists(classRoomDTO.getClassRoomName());
        Exception throwableException = null;
        ClassRoom createdClassRoom = null;

        if (isValidUser && !doesClassRoomExist) {
            createdClassRoom = createEntityFromDTO(classRoomDTO);
            var newClassRoom =  classRoomDAO.createNewClassRoom(createdClassRoom);
            return interChangeServiceResponse(newClassRoom,createDTOFromEntity(newClassRoom.getServiceData()));
        }
        return createServiceResponse(null
                , null
                , !isValidUser ? "Invalid User - "+classRoomDTO.getClassRoomDescription().toString() : "Classroom name -"+classRoomDTO.getClassRoomName()+" is already taken"
                , HttpStatus.NOT_FOUND
                , false
        );
    }

    private ServiceResponse<ClassRoomDTO> getClassRoomByClassRoomID(UUID classRoomID){
        var classRoom = classRoomDAO.getClassRoomForClassRoomID(classRoomID);
        return interChangeServiceResponse(classRoom,createDTOFromEntity(classRoom.getServiceData())) ;
    }

    private  ServiceResponse<ClassRoomDTO> modifyClassRoomDetails(ClassRoomDTO classRoomDTO){
        var originalClassRoom = classRoomRepository.findById(classRoomDTO.getClassRoomId());
        boolean userValid = false;
        boolean classRoomNameAvailable = false;
        if(originalClassRoom.isPresent()){
            var classRoomObject = originalClassRoom.get();
            userValid = userService.userExists(classRoomDTO.getClassRoomInchargeID());
            classRoomNameAvailable = classRoomObject.getClassRoomName().compareTo(classRoomDTO.getClassRoomName()) == 0 || !this.classRoomExists(classRoomDTO.getClassRoomName());
            if(userValid && classRoomNameAvailable){
                classRoomObject.setClassRoomName(classRoomDTO.getClassRoomName());
                classRoomObject.setClassRoomInchargeID(entityManager.getReference(User.class, classRoomDTO.getClassRoomInchargeID()));
                var updatedClassRoom = classRoomDAO.createNewClassRoom(classRoomObject);
                return interChangeServiceResponse(updatedClassRoom, createDTOFromEntity(updatedClassRoom.getServiceData()));
            }
        }
        return createServiceResponse(classRoomDTO
                , null
                , originalClassRoom.isEmpty() ? "Invalid ClassRoom ID - "+classRoomDTO.getClassRoomId().toString()+ " "
                    : !userValid? "invalid user ID  - "+classRoomDTO.getClassRoomInchargeID().toString()+" "
                        : "Classroom name is not available"
                , HttpStatus.NOT_FOUND
                ,false
        );
    }

    private ServiceResponse<ClassRoomDTO> deleteClassRoom(UUID classRoomID){
        var deletedClassRoom = classRoomDAO.deleteClassRoom(classRoomID);
        return interChangeServiceResponse(deletedClassRoom,createDTOFromEntity(deletedClassRoom.getServiceData()));
    }



    protected boolean classRoomExists(String ClassRoomName){
        List<ClassRoom> classRoom = classRoomRepository.findClassRoomByClassRoomName(ClassRoomName);
        return !classRoom.isEmpty();
    }

    protected boolean classRoomExists(UUID classRoomId){
        var classRoom = classRoomRepository.findById(classRoomId);
        return classRoom.isPresent();
    }

    protected ClassRoom getFullClassRoomReference(UUID classRoomID) throws Exception {
        //  call this only if the classroom with the ID exists.
        var classRoom = classRoomDAO.getClassRoomForClassRoomID(classRoomID);
        if(classRoom.getException() != null) throw classRoom.getException();
        return classRoom.getServiceData();
    }

    protected ClassRoom createEntityFromDTO(ClassRoomDTO classRoomDTO){
        if(classRoomDTO == null) return  null;
        ClassRoom classRoom = new ClassRoom();
        classRoom.setClassRoomId(classRoomDTO.getClassRoomId());
        classRoom.setClassRoomDescription(classRoomDTO.getClassRoomDescription());
        classRoom.setClassRoomName(classRoomDTO.getClassRoomName());
        classRoom.setClassRoomInchargeID(entityManager.getReference(User.class, classRoomDTO.getClassRoomInchargeID()));
        classRoom.setClassRoomCreatedTime(LocalDateTime.now());
        return classRoom;
    }
    protected ClassRoom createEntityFromDTO(ClassRoomRegistrationDTO classRoomDTO){
        if(classRoomDTO == null) return null;
        ClassRoom classRoom = new ClassRoom();
        classRoom.setClassRoomId(UUID.randomUUID());
        classRoom.setClassRoomDescription(classRoomDTO.getClassRoomDescription());
        classRoom.setClassRoomName(classRoomDTO.getClassRoomName());
        classRoom.setClassRoomInchargeID(entityManager.getReference(User.class, classRoomDTO.getClassRoomInchargeID()));
        classRoom.setClassRoomCreatedTime(LocalDateTime.now());
        return classRoom;
    }
    protected ClassRoomDTO createDTOFromEntity(ClassRoom classRoom){
        if(classRoom == null) return null;
        ClassRoomDTO classRoomDTO = new ClassRoomDTO();
        classRoomDTO.setClassRoomId(classRoom.getClassRoomId());
        classRoomDTO.setClassRoomInchargeID(classRoom.getClassRoomIncharge().getUserId());
        classRoomDTO.setClassRoomDescription(classRoom.getClassRoomDescription());
        classRoomDTO.setClassRoomName(classRoom.getClassRoomName());
        classRoomDTO.setClassRoomInchargeDetails(userService.convertDTOFromEntity(classRoom.getClassRoomIncharge()));
        return classRoomDTO;
    }
}
