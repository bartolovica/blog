package antun.bart.blog.repository;

import antun.bart.blog.model.Post;
import antun.bart.blog.model.PostDto;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Transactional
@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    @Query("SELECT new antun.bart.blog.model.PostDto( " +
            "p.id, " +
            "p.title, " +
            "p.content, " +
            "p.author, " +
            "p.createdAt) " +
            "FROM antun.bart.blog.model.Post p")
    Page<PostDto> getAllPosts(PageRequest pageRequest);
}
