package antun.bart.blog.model.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class CreatePostSuccessResponseDto {

    private static final String CREATE_POST_SUCCESS_RESPONSE = "Author %s Successfully created: %s new blog with title %s post at %s";

    private Integer postId;
    private String title;
    private String author;
    private Date createdAt;
    private String message;

    public CreatePostSuccessResponseDto(Integer postId, String title, String author, Date createdAt) {
        this.postId = postId;
        this.author = title;
        this.message = String.format(CREATE_POST_SUCCESS_RESPONSE, author, postId, title, createdAt);
    }
}
