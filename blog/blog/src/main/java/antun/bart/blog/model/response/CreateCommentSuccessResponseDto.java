package antun.bart.blog.model.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class CreateCommentSuccessResponseDto {

    private static final String CREATE_COMMENT_SUCCESS_RESPONSE = "Author: %s Successfully created comment with title: %s," +
            " that belongs to post: %s, at: %s";

    private Integer commentId;
    private String postTitle;
    private String author;
    private Date createdAt;
    private String message;

    public CreateCommentSuccessResponseDto(Integer commentId, String title, String author, Date createdAt) {
        this.commentId = commentId;
        this.postTitle = title;
        this.author = author;
        this.createdAt = createdAt;
        this.message = String.format(CREATE_COMMENT_SUCCESS_RESPONSE, author, commentId, postTitle, createdAt);
    }
}
