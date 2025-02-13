package antun.bart.blog.service;

import antun.bart.blog.exception.PostNotFoundException;
import antun.bart.blog.mapper.CreateCommentRequestToCommentMapper;
import antun.bart.blog.mapper.CreatePostRequestDtoToPostMapper;
import antun.bart.blog.model.entity.Comment;
import antun.bart.blog.model.entity.Post;
import antun.bart.blog.model.request.CreateCommentRequest;
import antun.bart.blog.model.request.CreatePostRequestDto;
import antun.bart.blog.model.response.CreateCommentSuccessResponse;
import antun.bart.blog.model.response.CreatePostSuccessResponse;
import antun.bart.blog.model.response.DeletePostSuccessResponse;
import antun.bart.blog.repository.CommentRepository;
import antun.bart.blog.repository.PostRepository;
import antun.bart.blog.service.impl.BlogWriteServiceImpl;
import antun.bart.blog.util.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class BlogWriteServiceImplTest {

    @Mock
    private PostRepository postRepository;

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private CreatePostRequestDtoToPostMapper postMapper;

    @Mock
    private CreateCommentRequestToCommentMapper commentMapper;

    @InjectMocks
    private BlogWriteServiceImpl blogWriteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createNewPostSuccessfully() {
        CreatePostRequestDto requestDto = TestUtils.createPostRequestDto();
        Post post = TestUtils.createPost();
        when(postMapper.createPostRequestDtoToPostMapping(requestDto)).thenReturn(post);
        when(postRepository.save(any(Post.class))).thenReturn(post);

        CreatePostSuccessResponse response = blogWriteService.createNewPost(requestDto);

        assertEquals(TestUtils.POST_ID, response.getPostId());
        assertEquals(TestUtils.POST_TITLE, response.getTitle());
        assertEquals(TestUtils.POST_AUTHOR, response.getAuthor());
        verify(postRepository, times(1)).save(post);
    }

    @Test
    void createNewCommentSuccessfully() throws PostNotFoundException {
        CreateCommentRequest request = TestUtils.createCommentRequest();
        Post post = TestUtils.createPost();
        Comment comment = TestUtils.createComment(post);
        when(postRepository.findById(TestUtils.POST_ID)).thenReturn(Optional.of(post));
        when(commentMapper.createCommentRequestToCommentMapping(request)).thenReturn(comment);
        when(commentRepository.save(any(Comment.class))).thenReturn(comment);

        CreateCommentSuccessResponse response = blogWriteService.createNewComment(request, TestUtils.COMMENT_AUTHOR);

        assertEquals(TestUtils.COMMENT_ID, response.getCommentId());
        assertEquals(TestUtils.POST_TITLE, response.getPostTitle());
        assertEquals(TestUtils.COMMENT_AUTHOR, response.getAuthor());
        verify(commentRepository, times(1)).save(comment);
    }

    @Test
    void createNewCommentPostNotFound() {
        CreateCommentRequest request = TestUtils.createCommentRequest();
        when(postRepository.findById(TestUtils.POST_ID)).thenReturn(Optional.empty());

        assertThrows(PostNotFoundException.class, () -> blogWriteService.createNewComment(request, TestUtils.COMMENT_AUTHOR));
    }

    @Test
    void deleteBlogPostSuccessfully() throws PostNotFoundException {
        Post post = TestUtils.createPost();
        when(postRepository.findById(TestUtils.POST_ID)).thenReturn(Optional.of(post));

        DeletePostSuccessResponse response = blogWriteService.deleteBlogPost(TestUtils.POST_ID);

        assertEquals(TestUtils.POST_ID, response.getPostId());
        verify(postRepository, times(1)).deleteById(TestUtils.POST_ID);
    }

    @Test
    void deleteBlogPostNotFound() {
        when(postRepository.findById(TestUtils.POST_ID)).thenReturn(Optional.empty());

        assertThrows(PostNotFoundException.class, () -> blogWriteService.deleteBlogPost(TestUtils.POST_ID));
    }
}