package com.sonichollow.forum.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sonichollow.forum.dto.Result;
import com.sonichollow.forum.entity.Post;

/**
 * @author admin
 * @description 针对表【post】的数据库操作Service
 * @createDate 2022-04-18 16:21:26
 */
public interface PostService extends IService<Post> {
    int PublishPost(Post post);
    void updatePost(Post post);
    Post getPostByPid(int pid);
    Result clickLikes(int pid, int uid);
    boolean getLikeStatus(Post post,String username);
}

