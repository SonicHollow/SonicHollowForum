package com.sonichollow.forum.service;

import com.sonichollow.forum.entity.Post;

public interface IPostService {
    /**
     *
     * @param post
     */

    int PublishPost(Post post);
    Post getPostByPid(int pid);
//    Integer EditPost (Post post) ;

}
