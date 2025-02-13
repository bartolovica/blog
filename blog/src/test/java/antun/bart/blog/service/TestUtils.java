package antun.bart.blog.util;

import antun.bart.blog.model.entity.Comment;
import antun.bart.blog.model.entity.Post;
import antun.bart.blog.model.request.CreateCommentRequest;
import antun.bart.blog.model.request.CreatePostRequestDto;

import java.time.LocalDateTime;
import java.util.Set;

public class TestUtils {

    public static final Integer POST_ID = 1;
    public static final String POST_TITLE = "Title";
    public static final String POST_CONTENT = "Content";
    public static final String POST_AUTHOR = "Author";
    public static final LocalDateTime POST_CREATED_AT = LocalDateTime.now();

    public static final Integer COMMENT_ID = 1;
    public static final String COMMENT_CONTENT = "Content";
    public static final String COMMENT_AUTHOR = "User";

    public static Post createPost() {
        return Post.builder()
                .postId(POST_ID)
                .title(POST_TITLE)
                .content(POST_CONTENT)
                .author(POST_AUTHOR)
                .createdAt(POST_CREATED_AT)
                .comments(Set.of())
                .build();
    }

    public static Comment createComment(Post post) {
        return Comment.builder()
                .commentId(COMMENT_ID)
                .author(COMMENT_AUTHOR)
                .post(post)
                .text(COMMENT_CONTENT)
                .createdAt(POST_CREATED_AT)
                .build();
    }

    public static CreatePostRequestDto createPostRequestDto() {
        return CreatePostRequestDto.builder()
                .title(POST_TITLE)
                .content(POST_CONTENT)
                .author(POST_AUTHOR)
                .build();
    }

    public static CreateCommentRequest createCommentRequest() {
        return CreateCommentRequest.builder()
                .postId(POST_ID)
                .content(COMMENT_CONTENT)
                .author(COMMENT_AUTHOR)
                .build();
    }
}