package antun.bart.blog.model.request;

import jakarta.validation.Valid;
import lombok.Builder;
import lombok.Data;

@Valid
@Data
@Builder(toBuilder = true)
public class CreatePostRequestDto {

    private String title;
    private String content;
    private String author;
}
