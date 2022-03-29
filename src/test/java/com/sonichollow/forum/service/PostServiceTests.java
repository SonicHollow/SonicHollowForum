package com.sonichollow.forum.service;

import com.sonichollow.forum.entity.Post;
import com.sonichollow.forum.entity.User;
import com.sonichollow.forum.service.ex.ServiceException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class PostServiceTests {
    @Autowired
    private IPostService postService;

    @Test
    public void publish() {
        try {
            Post post = new Post();
            post.setContent("This is a sentence");
            post.setCreatedTime(new Date());
            postService.PublishPost(post);
            System.out.println("OK");
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }
}
