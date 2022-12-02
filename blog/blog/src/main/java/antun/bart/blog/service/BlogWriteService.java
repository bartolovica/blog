package antun.bart.blog.service;

import antun.bart.blog.model.request.CreateCommentRequest;
import antun.bart.blog.model.request.CreatePostRequestDto;
import antun.bart.blog.model.response.CreateCommentSuccessResponseDto;
import antun.bart.blog.model.response.CreatePostSuccessResponseDto;
import antun.bart.blog.model.response.DeletePostSuccessResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface BlogWriteService {


    /**
     * create new Post
     *
     * @param createPostRequestDto
     * @return
     */
    CreatePostSuccessResponseDto createNewPost(CreatePostRequestDto createPostRequestDto);


    /**
     * create new Comment and refill author with username
     *
     * @param createCommentRequest
     * @param userName
     * @return
     * @throws Exception
     */
    CreateCommentSuccessResponseDto createNewComment(CreateCommentRequest createCommentRequest, String userName) throws Exception;

    /**
     * Delete Post by post id
     *
     * @param postId
     * @throws Exception
     */
    DeletePostSuccessResponseDto deleteBlogPost(Integer postId) throws Exception;
}
