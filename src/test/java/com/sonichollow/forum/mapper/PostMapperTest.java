package com.sonichollow.forum.mapper;

import com.sonichollow.forum.entity.Post;
import com.sonichollow.forum.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;

@SuppressWarnings("ALL")
@SpringBootTest
//@Runwith(SpringRunner.class)
public class PostMapperTest {
    @Autowired
    private PostMapper postMapper;

    @Test
    public void insert() {
        Post post = new Post();
        post.setContent("This is a sentence");
        post.setCreatedTime(new Date());
        Integer rows = postMapper.insertPost(post);
        System.out.println(rows);
    }

}
