package com.sonichollow.forum.service;

import com.sonichollow.forum.entity.Post;
import com.sonichollow.forum.entity.User;
import com.sonichollow.forum.service.ex.ServiceException;
import com.sonichollow.forum.service.impl.PostServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class PostServiceTests {
    @Autowired
    private PostServiceImpl postService;

    @Test
    public void testGetCount() {
        //查询总记录数
        int count = postService.count();
        System.out.println("Total count:"+ count);
    }

    @Test
    public void testPublish() {
        try {
            Post post=new Post();
            post.setPostName("aaa");
            post.setUsername("bbb");
            post.setText("This is a post");
            postService.PublishPost(post);
            System.out.println("发布成功");
        }
        catch (ServiceException e){
            System.err.println(e);
        }
    }

    @Test
    public void testUpdate(){
        Post post=new Post();
        post.setPostName("aaa");
        post.setUsername("bbb");
        post.setText("This is a post");
        post.setModifiedTime(new Date());
        postService.updatePost(post);
        System.out.println(post.getText());
    }
}
