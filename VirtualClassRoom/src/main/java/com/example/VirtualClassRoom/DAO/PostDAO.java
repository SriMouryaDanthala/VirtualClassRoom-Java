package com.example.VirtualClassRoom.DAO;

import com.example.VirtualClassRoom.genericDatatypes.ServiceResponse;
import com.example.VirtualClassRoom.interfaces.IServiceResponse;
import com.example.VirtualClassRoom.models.ClassRoom;
import com.example.VirtualClassRoom.models.Post;
import com.example.VirtualClassRoom.models.User;
import com.example.VirtualClassRoom.repository.PostRepository;
import jakarta.persistence.EntityManager;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class PostDAO implements IServiceResponse {
    private final PostRepository postRepository;
    private final EntityManager entityManager;

    public PostDAO(PostRepository postRepository, EntityManager entityManager) {
        this.postRepository = postRepository;
        this.entityManager = entityManager;
    }

    public ServiceResponse<Post> createPost(Post post) {
        return this.createOrUpdatePost(post);
    }

    public ServiceResponse<Post> updatePost(Post post) {
        return this.createOrUpdatePost(post);
    }

    public ServiceResponse<Post> removePost(Post post) {
        return this.deletePost(post);
    }

    public ServiceResponse<Post> retrievePost(UUID postId) {
        return this.getPostByPostID(postId);
    }

    public ServiceResponse<List<Post>> getPostsInClassRoom(UUID classRoom) {
        return this.getAllPostsInClassRoom(classRoom);
    }

    public ServiceResponse<List<Post>> getPostsOfUser(UUID userID){
        return this.getAllPostsByUser(userID);
    }

    private ServiceResponse<Post> createOrUpdatePost(Post post) {
        Exception throwableException = null;
        try{
            postRepository.save(post);
        }
        catch (Exception e){
            throwableException = e;
        }
        return createServiceResponse(
                post,
                throwableException,
                throwableException != null ? throwableException.getMessage() : "",
                throwableException != null ? HttpStatus.INTERNAL_SERVER_ERROR : HttpStatus.CREATED,
                throwableException == null
        );

    }

    private ServiceResponse<Post> deletePost(Post post) {
        Exception throwableException = null;
        try{
            postRepository.delete(post);
        }
        catch (Exception e){
            throwableException = e;
        }
        return createServiceResponse(
                post,
                throwableException,
                throwableException != null ? throwableException.getMessage() : "",
                throwableException != null ? HttpStatus.INTERNAL_SERVER_ERROR : HttpStatus.GONE,
                throwableException == null
        );
    }

    private ServiceResponse<Post> getPostByPostID(UUID postID){
        Exception throwableException = null;
        Post post = null;
        try{
            var response = postRepository.findById(postID);
            if(response.isPresent()){
                post = response.get();
            }
        }
        catch (Exception e){
            throwableException = e;
        }
        return createServiceResponse(
                post,
                throwableException,
                throwableException !=null ? throwableException.getMessage() : "",
                throwableException != null ? HttpStatus.INTERNAL_SERVER_ERROR : HttpStatus.OK,
                throwableException == null
        );
    }

    private ServiceResponse<List<Post>> getAllPostsInClassRoom(UUID classRoomID) {
        Exception throwableException = null;
        List<Post> posts = null;
        try{
            posts = postRepository.getPostsByPostClassRoom(entityManager.getReference(ClassRoom.class, classRoomID));
        }
        catch(Exception e){
            throwableException = e;
        }
        return createServiceResponse(
                posts,
                throwableException,
                throwableException != null ?  throwableException.getMessage(): "",
                throwableException !=null ? HttpStatus.INTERNAL_SERVER_ERROR : HttpStatus.OK,
                throwableException == null
        );
    }

    private ServiceResponse<List<Post>> getAllPostsByUser(UUID UserID) {
        List<Post> posts = null;
        Exception throwableException = null;
        try{
            posts = postRepository.getPostByPostUser(entityManager.getReference(User.class, UserID));
        }
        catch(Exception e){
            throwableException = e;
        }
        return createServiceResponse(
                posts,
                throwableException,
                throwableException != null ?  throwableException.getMessage(): "",
                throwableException !=null ? HttpStatus.INTERNAL_SERVER_ERROR : HttpStatus.OK,
                throwableException == null
        );
    }
}
