package antun.bart.blog.mapper;

import antun.bart.blog.model.entity.Comment;
import antun.bart.blog.model.request.CreateCommentRequest;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring")
public abstract class CreateCommentRequestToCommentMapper {

    public abstract Comment createCommentRequestToCommentMapping(CreateCommentRequest createCommentRequest);

//    @AfterMapping
//    public void mapCreatedAt(@MappingTarget Comment comment) {
//        comment.setCreatedAt(LocalDateTime.now());
//    }
}
