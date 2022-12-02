package antun.bart.blog.controller;

import antun.bart.blog.model.request.CreateCommentRequest;
import antun.bart.blog.model.request.CreatePostRequestDto;
import antun.bart.blog.model.response.CreateCommentSuccessResponseDto;
import antun.bart.blog.model.response.CreatePostSuccessResponseDto;
import antun.bart.blog.service.BlogWriteService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import static antun.bart.blog.controller.ControllerDefinition.*;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping(WRITE_API)
public class BlogWriteController {

    private final BlogWriteService blogWriteService;

    private static final String HAS_ADMIN_ROLE = "hasRole('ADMIN')";

    public BlogWriteController(BlogWriteService blogWriteService) {
        this.blogWriteService = blogWriteService;
    }

    @PreAuthorize(HAS_ADMIN_ROLE)
    @PostMapping
    public ResponseEntity<CreatePostSuccessResponseDto> createPost(@RequestBody CreatePostRequestDto createPostRequestDto) {
        return ok(blogWriteService.createNewPost(createPostRequestDto));
    }

    @PreAuthorize("hasRole('VISITOR')")
    @PostMapping(COMMENT)
    public ResponseEntity createComment(@RequestBody CreateCommentRequest createCommentRequest, Authentication authentication) throws Exception {
        return ok(blogWriteService.createNewComment(createCommentRequest, authentication.getName()));
    }

    @PreAuthorize(HAS_ADMIN_ROLE)
    @DeleteMapping(POST_ID)
    public ResponseEntity deletePost(@PathVariable Integer postId) throws Exception {
        return ok(blogWriteService.deleteBlogPost(postId));
    }
}
