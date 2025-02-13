package antun.bart.blog.service.impl;

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
import antun.bart.blog.service.BlogWriteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class BlogWriteServiceImpl implements BlogWriteService {

    private static final String POST_ID_ERROR_MSG = "Post with id %s doesn't exist";

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final CreatePostRequestDtoToPostMapper postMapper;
    private final CreateCommentRequestToCommentMapper commentMapper;

    @Override
    @Transactional
    public CreatePostSuccessResponse createNewPost(CreatePostRequestDto createPostRequestDto) {
        log.info("Creating a new post with title: {}", createPostRequestDto.getTitle());
        Post newPost = postMapper.createPostRequestDtoToPostMapping(createPostRequestDto);
        Post post = postRepository.save(newPost);
        return new CreatePostSuccessResponse(post.getPostId(), post.getTitle(), post.getAuthor(), post.getCreatedAt());
    }

    @Override
    @Transactional
    public CreateCommentSuccessResponse createNewComment(CreateCommentRequest createCommentRequest, String userName) throws PostNotFoundException {
        log.info("Creating a new comment for post id: {}", createCommentRequest.getPostId());
        Integer postId = createCommentRequest.getPostId();
        createCommentRequest.setAuthor(userName);
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException(String.format(POST_ID_ERROR_MSG, postId)));
        Comment newComment = commentMapper.createCommentRequestToCommentMapping(createCommentRequest);
        newComment.setPost(post);
        Comment comment = commentRepository.save(newComment);
        return new CreateCommentSuccessResponse(comment.getCommentId(), comment.getPost().getTitle(),
                comment.getAuthor(), comment.getCreatedAt());
    }

    @Override
    @Transactional
    public DeletePostSuccessResponse deleteBlogPost(Integer postId) throws PostNotFoundException {
        log.info("Deleting post with id: {}", postId);
        postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException(String.format(POST_ID_ERROR_MSG, postId)));
        postRepository.deleteById(postId);
        return new DeletePostSuccessResponse(postId);
    }
}