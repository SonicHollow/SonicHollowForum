package com.sonichollow.forum.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sonichollow.forum.entity.Post;
import com.sonichollow.forum.service.ex.ServiceException;
import org.junit.jupiter.api.DisplayName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostServiceTests {
    @Autowired
    private PostService postService;


    @Resource
    private RedisTemplate<String,Object> redisTemplate;


    @Resource
    private StringRedisTemplate stringRedisTemplate;


    @Test
    // the number of post
    public void testGetCount() {
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
            System.out.println("publish successfully");
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

    // Test for connection to redis
    @DisplayName("RedisTest")
    @Test
    public void testString(){
        redisTemplate.opsForValue().set("name","Bob");
        Object name = redisTemplate.opsForValue().get("name");
        System.out.println("name = " + name);
    }

    @Test
    public void testHash() {
        stringRedisTemplate.opsForHash().put("2","name","Alice");
        stringRedisTemplate.opsForHash().put("2","age","250");
        Map<Object,Object> map = stringRedisTemplate.opsForHash().entries("1");
        System.out.println(map);
    }

    //Test whether objects can be stored
    @Test
    public void testSavePost() {
        Post post = new Post();
        post.setUsername("Alice");
        post.setText("This is a test");

        redisTemplate.opsForValue().set("name:100",post);
        Post name = (Post) redisTemplate.opsForValue().get("name:100");
        System.out.println("name = " + name);
    }


    @Test
    public void testClickLikes(){
        System.out.println(postService.clickLikes(41,94));
//        redisTemplate.opsForValue().set(pid +":likes", uid);
//        Object result = redisTemplate.opsForValue().get(pid +":likes");
//        System.out.println("result = "+ result);
    }

    @Test
    public void testGetPostByPid(){
        Post post = postService.getPostByPid(42);
        System.out.println(post);
    }

    @Test
    public void testLikeStatus(){
        Post post = postService.getPostByPid(42);
        System.out.println(postService.getLikeStatus(post,"Tim"));
        System.out.println("Test Successfully");
    }
}
