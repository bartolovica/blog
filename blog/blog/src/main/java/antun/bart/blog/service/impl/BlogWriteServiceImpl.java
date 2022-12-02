package antun.bart.blog.service.impl;

import antun.bart.blog.exception.PostNotFoundException;
import antun.bart.blog.mapper.CreateCommentRequestToCommentMapper;
import antun.bart.blog.mapper.CreatePostRequestDtoToPostMapper;
import antun.bart.blog.model.*;
import antun.bart.blog.model.request.CreateCommentRequest;
import antun.bart.blog.model.request.CreatePostRequestDto;
import antun.bart.blog.model.response.CreateCommentSuccessResponseDto;
import antun.bart.blog.model.response.CreatePostSuccessResponseDto;
import antun.bart.blog.model.response.DeletePostSuccessResponseDto;
import antun.bart.blog.repository.CommentRepository;
import antun.bart.blog.repository.PostRepository;
import antun.bart.blog.service.BlogWriteService;
import org.springframework.stereotype.Service;

@Service
public class BlogWriteServiceImpl implements BlogWriteService {

    private static final String POST_ID_ERROR_MSG = "Post with %s id doesn't exists";

    private final PostRepository postRepository;

    private final CommentRepository commentRepository;

    private final CreatePostRequestDtoToPostMapper postMapper;

    private final CreateCommentRequestToCommentMapper commentMapper;

    public BlogWriteServiceImpl(final PostRepository postRepository,
                                final CommentRepository commentRepository,
                                final CreatePostRequestDtoToPostMapper postMapper,
                                final CreateCommentRequestToCommentMapper commentMapper) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.postMapper = postMapper;
        this.commentMapper = commentMapper;
    }

    @Override
    public CreatePostSuccessResponseDto createNewPost(CreatePostRequestDto createPostRequestDto) {
        Post newPost = postMapper.CreatePostRequestDtoToPostMapping(createPostRequestDto);
        Post post = postRepository.save(newPost);
        return new CreatePostSuccessResponseDto(post.getPostId(), post.getTitle(), post.getAuthor(), post.getCreatedAt());
    }

    @Override
    public CreateCommentSuccessResponseDto createNewComment(CreateCommentRequest createCommentRequest, String userName) {
        Integer postId = createCommentRequest.getPostId();
        createCommentRequest.setAuthor(userName);
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException(String.format(POST_ID_ERROR_MSG, postId)));
        Comment newComment = commentMapper.CreateCommentRequestToCommentMapping(createCommentRequest);
        newComment.setPost(post);
        Comment comment = commentRepository.save(newComment);
        return new CreateCommentSuccessResponseDto(comment.getCommentId(), comment.getPost().getTitle(),
                comment.getAuthor(), comment.getCreatedAt());
    }

    @Override
    public DeletePostSuccessResponseDto deleteBlogPost(Integer postId) {
        postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException(String.format(POST_ID_ERROR_MSG, postId)));
        postRepository.deleteById(postId);
        return new DeletePostSuccessResponseDto(postId);
    }
}
