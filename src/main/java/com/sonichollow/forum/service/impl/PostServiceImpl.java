package com.sonichollow.forum.service.impl;

import com.sonichollow.forum.entity.Post;
import com.sonichollow.forum.mapper.PostMapper;
import com.sonichollow.forum.service.IPostService;
import com.sonichollow.forum.service.ex.InsertException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * post的业务层
 */
@Service
public class PostServiceImpl implements IPostService {
    @Autowired
    private PostMapper postMapper;


    //发帖
    @Override
    public int PublishPost(Post post) {

        //构造帖子
        post.setCreatedTime(new Date());
        post.setReplyTime(new Date());
        post.setReplyCount(0);

        //插入帖子
        postMapper.insertPost(post);
        System.out.println(post.getPid());

        //更新用户发帖量
        return post.getPid();
    }

    public Post getPostByPid(int pid) {

        Post post =postMapper.getPostByPid(pid);

        return post;
    }


}
