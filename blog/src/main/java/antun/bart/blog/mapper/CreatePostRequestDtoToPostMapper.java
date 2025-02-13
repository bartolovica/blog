package antun.bart.blog.mapper;

import antun.bart.blog.model.entity.Post;
import antun.bart.blog.model.request.CreatePostRequestDto;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring")
public abstract class CreatePostRequestDtoToPostMapper {

    @Mapping(source = "title", target = "title")
    public abstract Post createPostRequestDtoToPostMapping(CreatePostRequestDto createPostRequestDto);

//    @AfterMapping
//    public void mapCreatedAt(@MappingTarget Post post) {
//        post.setCreatedAt(LocalDateTime.now());
//    }
}
