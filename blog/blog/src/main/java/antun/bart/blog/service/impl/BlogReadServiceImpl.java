package antun.bart.blog.service.impl;

import antun.bart.blog.model.Post;
import antun.bart.blog.model.PostDto;
import antun.bart.blog.repository.PostRepository;
import antun.bart.blog.service.BlogReadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class BlogReadServiceImpl implements BlogReadService {

    Logger LOG = LoggerFactory.getLogger(BlogReadServiceImpl.class);

    private static final String SORT_POSTS_BY_CREATED_AT = "createdAt";

    private final PostRepository postRepository;

    public BlogReadServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public List<Post> getAllPostFromBlog() {
        return postRepository.findAll();
    }

    @Override
    public List<PostDto> getAllPostsFromBlogSorted(Integer pageNo, Integer pageSize) {

        PageRequest pageRequest = PageRequest.of(pageNo, 1, Sort.by(Sort.Direction.ASC, SORT_POSTS_BY_CREATED_AT));
        Page<PostDto> page = postRepository.getAllPosts(pageRequest);

        if (page.hasContent()) {
            return page.getContent();
        } else {
            LOG.info("Paged result has no content!");
            return new ArrayList<PostDto>();
        }
    }
}
