package antun.bart.blog.mapper;

import antun.bart.blog.model.Comment;
import antun.bart.blog.model.request.CreateCommentRequest;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.Date;

@Mapper(componentModel = "spring")
public abstract class CreateCommentRequestToCommentMapper {

    public abstract Comment CreateCommentRequestToCommentMapping(CreateCommentRequest createCommentRequest);

    @AfterMapping
    public void mapCreatedAt(@MappingTarget Comment comment) {
        comment.setCreatedAt(new Date());
    }
}
