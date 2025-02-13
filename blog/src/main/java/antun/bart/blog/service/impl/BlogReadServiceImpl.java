package antun.bart.blog.service.impl;

import antun.bart.blog.model.entity.Post;
import antun.bart.blog.model.entity.PostDto;
import antun.bart.blog.repository.PostRepository;
import antun.bart.blog.service.BlogReadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@Slf4j
@RequiredArgsConstructor
public class BlogReadServiceImpl implements BlogReadService {

    private static final String SORT_POSTS_BY_CREATED_AT = "createdAt";

    private final PostRepository postRepository;

    @Override
    public List<Post> getAllPostFromBlog() {
        return postRepository.findAll();
    }

    @Override
    public List<PostDto> getAllPostsFromBlogSorted(Integer pageNo, Integer pageSize) {

        PageRequest pageRequest = PageRequest.of(pageNo, pageSize, Sort.by(Sort.Direction.ASC, SORT_POSTS_BY_CREATED_AT));
        Page<PostDto> page = postRepository.getAllPosts(pageRequest);

        return Optional.of(page)
                .filter(Page::hasContent)
                .map(Page::getContent)
                .orElseGet(() -> {
                    log.info("Paged result has no content!");
                    return List.of();
                });
    }
}
