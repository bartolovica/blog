package antun.bart.blog.model.response;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@JsonDeserialize
@AllArgsConstructor
@Builder(toBuilder = true)
public class CreateCommentSuccessResponse {

    private static final String CREATE_COMMENT_SUCCESS_RESPONSE = "Author: %s Successfully created comment with title: %s," +
            " that belongs to post: %s, at: %s";

    Integer commentId;
    String postTitle;
    String author;
    LocalDateTime createdAt;
    String message;

    public CreateCommentSuccessResponse(Integer commentId, String title, String author, LocalDateTime createdAt) {
        this.commentId = commentId;
        this.postTitle = title;
        this.author = author;
        this.createdAt = createdAt;
        this.message = String.format(CREATE_COMMENT_SUCCESS_RESPONSE, author, commentId, postTitle, createdAt);
    }
}
