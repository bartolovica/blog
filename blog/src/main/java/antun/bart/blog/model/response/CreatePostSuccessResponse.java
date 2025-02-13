package antun.bart.blog.model.response;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@JsonDeserialize
@Builder(toBuilder = true)
public class CreatePostSuccessResponse {

    private static final String CREATE_POST_SUCCESS_RESPONSE = "Author %s Successfully created: %s new blog with title %s post at %s";

    Integer postId;
    String title;
    String author;
    LocalDateTime createdAt;
    String message;

    public CreatePostSuccessResponse(Integer postId, String title, String author, LocalDateTime createdAt) {
        this.postId = postId;
        this.title = title;
        this.author = author;
        this.message = String.format(CREATE_POST_SUCCESS_RESPONSE, author, postId, title, createdAt);
    }
}
