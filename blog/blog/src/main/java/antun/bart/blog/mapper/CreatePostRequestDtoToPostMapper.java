package antun.bart.blog.mapper;

import antun.bart.blog.model.request.CreatePostRequestDto;
import antun.bart.blog.model.Post;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.Date;

@Mapper(componentModel = "spring")
public abstract class CreatePostRequestDtoToPostMapper {

    @Mapping(source = "title", target = "title")
    public abstract Post CreatePostRequestDtoToPostMapping(CreatePostRequestDto createPostRequestDto);

    @AfterMapping
    public void mapCreatedAt(@MappingTarget Post post) {
        post.setCreatedAt(new Date());
    }
}
