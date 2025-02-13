package antun.bart.blog.service;

import antun.bart.blog.exception.PostNotFoundException;
import antun.bart.blog.model.request.CreateCommentRequest;
import antun.bart.blog.model.request.CreatePostRequestDto;
import antun.bart.blog.model.response.CreateCommentSuccessResponse;
import antun.bart.blog.model.response.CreatePostSuccessResponse;
import antun.bart.blog.model.response.DeletePostSuccessResponse;
import org.springframework.stereotype.Service;

@Service
public interface BlogWriteService {

    CreatePostSuccessResponse createNewPost(CreatePostRequestDto createPostRequestDto);

    CreateCommentSuccessResponse createNewComment(CreateCommentRequest createCommentRequest, String userName) throws PostNotFoundException;

    DeletePostSuccessResponse deleteBlogPost(Integer postId) throws PostNotFoundException;
}
