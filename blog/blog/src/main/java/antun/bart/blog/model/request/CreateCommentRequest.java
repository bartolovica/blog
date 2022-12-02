package antun.bart.blog.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CreateCommentRequest {

    @NotNull
    private Integer postId;
    @NotBlank
    private String title;
    private String content;
    private String author;
}
