package antun.bart.blog.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@EnableWebMvc
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class BlogExceptionHandler extends ResponseEntityExceptionHandler {

    Logger LOG = LoggerFactory.getLogger(BlogExceptionHandler.class);

    @ExceptionHandler(value
            = {PostNotFoundException.class})
    public ResponseEntity handlePostNotFound(PostNotFoundException e) {
        LOG.warn("Entity not found exception: {}", e.getMessage());
        return ResponseEntity.status(NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(e.getMessage());
    }

    @ExceptionHandler(value
            = {IllegalArgumentException.class})
    public ResponseEntity handleIllegalArgumentException(IllegalArgumentException e) {
        LOG.warn("IllegalArgumentException: {}", e.getMessage());
        return ResponseEntity.status(BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(e.getMessage());
    }
}
