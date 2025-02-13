package antun.bart.blog.controller;

import antun.bart.blog.model.entity.Post;
import antun.bart.blog.model.entity.PostDto;
import antun.bart.blog.service.BlogReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static antun.bart.blog.controller.ControllerDefinition.READ_API;
import static antun.bart.blog.controller.ControllerDefinition.SORTED;

@RestController
@RequestMapping(READ_API)
@RequiredArgsConstructor
public class BlogReadController {

    private final BlogReadService blogReadService;

    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts() {
        return ResponseEntity.ok(blogReadService.getAllPostFromBlog());
    }

    @GetMapping(SORTED)
    public ResponseEntity<List<PostDto>> getAllPostsSorted(
            @RequestHeader(defaultValue = "0") Integer pageNo,
            @RequestHeader(defaultValue = "10") Integer pageSize) {
        return ResponseEntity.ok(blogReadService.getAllPostsFromBlogSorted(pageNo, pageSize));
    }
}
