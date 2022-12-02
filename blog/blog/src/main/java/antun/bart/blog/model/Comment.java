package antun.bart.blog.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "COMMENT")
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
    private Date createdAt;

}
