package antun.bart.blog.controller;

import antun.bart.blog.model.request.CreateCommentRequest;
import antun.bart.blog.model.request.CreatePostRequestDto;
import antun.bart.blog.model.response.CreateCommentSuccessResponse;
import antun.bart.blog.model.response.CreatePostSuccessResponse;
import antun.bart.blog.model.response.DeletePostSuccessResponse;
import antun.bart.blog.service.BlogWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import static antun.bart.blog.controller.ControllerDefinition.*;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping(WRITE_API)
@RequiredArgsConstructor
public class BlogWriteController {

    private static final String HAS_ADMIN_ROLE = "hasRole('ADMIN')";

    private final BlogWriteService blogWriteService;

    @PreAuthorize(HAS_ADMIN_ROLE)
    @PostMapping
    public ResponseEntity<CreatePostSuccessResponse> createPost(@RequestBody CreatePostRequestDto createPostRequestDto) {
        return ok(blogWriteService.createNewPost(createPostRequestDto));
    }

    @PreAuthorize("hasRole('VISITOR')")
    @PostMapping(COMMENT)
    public ResponseEntity<CreateCommentSuccessResponse> createComment(@RequestBody CreateCommentRequest createCommentRequest, Authentication authentication) throws Exception {
        return ok(blogWriteService.createNewComment(createCommentRequest, authentication.getName()));
    }

    @PreAuthorize(HAS_ADMIN_ROLE)
    @DeleteMapping(POST_ID)
    public ResponseEntity<DeletePostSuccessResponse> deletePost(@PathVariable Integer postId) throws Exception {
        return ok(blogWriteService.deleteBlogPost(postId));
    }
}
