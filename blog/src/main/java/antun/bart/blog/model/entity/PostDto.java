package antun.bart.blog.model.entity;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder(toBuilder = true)
public record PostDto(Integer postId, String title, String content, String author, LocalDateTime createdAt) {
}