package com.example.VirtualClassRoom.service;

import com.example.VirtualClassRoom.DAO.PostDAO;
import com.example.VirtualClassRoom.dto.PostCreationDTO;
import com.example.VirtualClassRoom.dto.PostRetrievalDTO;
import com.example.VirtualClassRoom.genericDatatypes.ApiResponse;
import com.example.VirtualClassRoom.genericDatatypes.ServiceResponse;
import com.example.VirtualClassRoom.interfaces.IServiceResponse;
import com.example.VirtualClassRoom.models.Post;
import com.example.VirtualClassRoom.repository.PostRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class PostService implements IServiceResponse {
    private final UserService userService;
    private final ClassRoomService classRoomService;
    private final PostDAO postDAO;

    public PostService(UserService userService, ClassRoomService classRoomService, PostDAO postDAO) {
        this.userService = userService;
        this.classRoomService = classRoomService;
        this.postDAO = postDAO;
    }

    public ApiResponse<PostRetrievalDTO> createPost(PostCreationDTO postCreationDTO){
        return createApiResponse(this.addPost(postCreationDTO));
    }

    public ApiResponse<PostRetrievalDTO> removePost(UUID postID){
        return createApiResponse(this.deletePost(postID));
    }

    public ApiResponse<List<PostRetrievalDTO>> getUserPosts(UUID userID){
        return createApiResponse(this.getPostsOfUser(userID));
    }

    public ApiResponse<List<PostRetrievalDTO>> getClassRoomPosts(UUID classRoomID){
        return createApiResponse(this.getPostsOfClassroom(classRoomID));
    }


    private ServiceResponse<PostRetrievalDTO> addPost(PostCreationDTO newPost){
        UUID userID = newPost.getPostUser();
        UUID classRoomID = newPost.getPostClassRoom();
        Exception throwableException = null;
        boolean isAValidUser = userService.userExists(userID);
        boolean isAVaidClassRoom  = classRoomService.classRoomExists(classRoomID);

        if(isAValidUser && isAVaidClassRoom){
            try{
                Post post = createEntityFromDTO(newPost);
                var postServiceResp = postDAO.createPost(post);
                return interChangeServiceResponse(postServiceResp, createDToFromEntity(postServiceResp.getServiceData()));
            }
            catch(Exception e){
                throwableException = e;
            }
        }

        return createServiceResponse(
                null,
                throwableException != null ? throwableException : null,
                !isAValidUser ? "User is not valid - "+newPost.getPostUser().toString() :
                        !isAVaidClassRoom ? "Invalid ClassRoom "+newPost.getPostClassRoom() :
                                throwableException != null ? throwableException.getMessage() : "",
                throwableException != null ? HttpStatus.INTERNAL_SERVER_ERROR : HttpStatus.NOT_FOUND,
                false
        );
    }

    private ServiceResponse<PostRetrievalDTO> deletePost(UUID postID){
        Post post = null;
        Exception throwableException = null;
        boolean isValidPost = isAValidPost(postID);
        try {
            if (isValidPost) {
                var response = postDAO.retrievePost(postID);
                post = response.getServiceData();
                postDAO.removePost(post);
                return interChangeServiceResponse(response, createDToFromEntity(post));
            }
        }catch (Exception e){
            throwableException = e;
        }
        return createServiceResponse(
                null,
                throwableException,
                throwableException != null ? throwableException.getMessage() : !isValidPost ? "Not a valid post "+postID.toString() : "",
                throwableException != null ? HttpStatus.INTERNAL_SERVER_ERROR : HttpStatus.NOT_FOUND,
                false
        );
    }

    private ServiceResponse<List<PostRetrievalDTO>> getPostsOfUser(UUID userID){
        var isAValidUser = userService.userExists(userID);
        Exception throwableException = null;
        try {
            if(isAValidUser){
                var response = postDAO.getPostsOfUser(userID);
                List<PostRetrievalDTO> postDTOList = new ArrayList<>();
                for (Post post : response.getServiceData()) {
                    postDTOList.add(createDToFromEntity(post));
                }
                return interChangeServiceResponse(response, postDTOList);
            }
        }
        catch (Exception e){
            throwableException = e;
        }

        return createServiceResponse(
                null,
                throwableException,
                !isAValidUser ? "Invalid user - "+userID.toString() : throwableException != null ? throwableException.getMessage() : "",
                throwableException != null ? HttpStatus.INTERNAL_SERVER_ERROR : HttpStatus.NOT_FOUND,
                false
        );
    }

    private ServiceResponse<List<PostRetrievalDTO>> getPostsOfClassroom(UUID classRoomID){
        var isValidClassRoom = classRoomService.classRoomExists(classRoomID);
        Exception throwableException = null;
        try {
            if(isValidClassRoom){
                var response = postDAO.getPostsInClassRoom(classRoomID);
                List<PostRetrievalDTO> postDTOList = new ArrayList<>();
                for (Post post : response.getServiceData()) {
                    postDTOList.add(createDToFromEntity(post));
                }
                return interChangeServiceResponse(response, postDTOList);
            }
        }
        catch (Exception e){
            throwableException = e;
        }

        return createServiceResponse(
                null,
                throwableException,
                !isValidClassRoom ? "Invalid classroom - "+classRoomID.toString() : throwableException != null ? throwableException.getMessage() : "",
                throwableException != null ? HttpStatus.INTERNAL_SERVER_ERROR : HttpStatus.NOT_FOUND,
                false
        );
    }

    /*
    * for now, we are not allowing to edit the post. Hence, there is no end point for the update call and relevant methods.
    * */

    protected boolean isAValidPost(UUID postID){
        var resp =  postDAO.retrievePost(postID);
        return resp.isSuccess() &&  resp.getServiceData() != null;
    }

    protected PostRetrievalDTO createDToFromEntity(Post postEntity) {
        if(postEntity == null){ return  null; }
        PostRetrievalDTO postDTO = new PostRetrievalDTO();
        postDTO.setPostID(postEntity.getPostID());
        postDTO.setPostClassRoomID(postEntity.getPostClassRoom().getClassRoomId());
        postDTO.setPostClassRoomName(postEntity.getPostClassRoom().getClassRoomName());
        postDTO.setPostUserID(postEntity.getPostUser().getUserId());
        postDTO.setPostUserName(postEntity.getPostUser().getUserName());
        postDTO.setPostDate(postEntity.getPostDate());
        postDTO.setPostLikeCount(postEntity.getPostLikeCount());
        postDTO.setPostContent(postEntity.getPostContent());
        return postDTO;
    }

    protected Post createEntityFromDTO(PostCreationDTO postCreationDTO) throws Exception {
        if(postCreationDTO == null){
            return null;
        }
        Post post = new Post();
        post.setPostID(UUID.randomUUID());
        post.setPostContent(postCreationDTO.getPostContent());
        post.setPostUser(userService.getFullUserReference(postCreationDTO.getPostUser()));
        post.setPostClassRoom(classRoomService.getFullClassRoomReference(postCreationDTO.getPostClassRoom()));
        post.setPostDate(LocalDateTime.now());
        return post;
    }
}
