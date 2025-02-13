package antun.bart.blog.model.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Builder(toBuilder = true)
@AllArgsConstructor
@Data
@Valid
public class CreateCommentRequest {

    @NotNull
    Integer postId;
    @NotBlank
    String title;
    String content;
    String author;
}
