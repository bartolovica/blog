package antun.bart.blog.controller;

public final class ControllerDefinition {

    public static final String ROOT_API = "/blog";
    public static final String WRITE_API = ROOT_API + "/write";
    public static final String READ_API = ROOT_API + "/read";
    public static final String POST_ID = "/{postId}";
    public static final String COMMENT = "/comment";
    public static final String SORTED = "/sorted";
}
