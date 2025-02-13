package antun.bart.blog.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder(toBuilder = true)
@RequiredArgsConstructor
@Entity(name = "COMMENT")
@AllArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Integer commentId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "post_id")
    @JsonIgnoreProperties("comments")
    private Post post;

    @Column(name = "title")
    private String title;

    @Column(name = "text")
    private String text;

    @Column(name = "author")
    private String author;

    @Column(name = "createdAt")
    private LocalDateTime createdAt;

}
