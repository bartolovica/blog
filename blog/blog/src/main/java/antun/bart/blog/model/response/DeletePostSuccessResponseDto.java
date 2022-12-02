package antun.bart.blog.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeletePostSuccessResponseDto {

    private static final String DELETE_POST_RESPONSE = "Post with %s id successfully deleted";

    private Integer postId;
    private String message;

    public DeletePostSuccessResponseDto(Integer postId) {
        this.postId = postId;
        this.message = String.format(DELETE_POST_RESPONSE, postId);
    }
}
