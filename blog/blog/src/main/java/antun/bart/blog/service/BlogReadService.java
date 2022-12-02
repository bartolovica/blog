package antun.bart.blog.service;

import antun.bart.blog.model.Post;
import antun.bart.blog.model.PostDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BlogReadService {

    /**
     * get all posts
     *
     * @return
     */
    List<Post> getAllPostFromBlog();

    /**
     * List blog posts, supports pagination and sort by "created at"
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    List<PostDto> getAllPostsFromBlogSorted(Integer pageNo, Integer pageSize);
}
