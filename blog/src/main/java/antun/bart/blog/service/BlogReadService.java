package antun.bart.blog.service;

import antun.bart.blog.model.entity.Post;
import antun.bart.blog.model.entity.PostDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BlogReadService {

    List<Post> getAllPostFromBlog();

    List<PostDto> getAllPostsFromBlogSorted(Integer pageNo, Integer pageSize);
}
