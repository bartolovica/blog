package antun.bart.blog.model.response;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@JsonDeserialize
@AllArgsConstructor
@Builder(toBuilder = true)
public class DeletePostSuccessResponse {

    private static final String DELETE_POST_RESPONSE = "Post with %s id successfully deleted";

    Integer postId;
    String message;

    public DeletePostSuccessResponse(Integer postId) {
        this.postId = postId;
        this.message = String.format(DELETE_POST_RESPONSE, postId);
    }
}
