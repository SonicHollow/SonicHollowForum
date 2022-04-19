package com.sonichollow.forum.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sonichollow.forum.entity.Post;
import com.sonichollow.forum.entity.User;
import com.sonichollow.forum.service.ex.ServiceException;
import com.sonichollow.forum.service.impl.PostServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Date;
import java.util.Map;

@SpringBootTest
public class PostServiceTests {
    @Autowired
    private PostService postService;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


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
//            System.out.println(postService.getById(2));
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

    //简单测试能否连接到redis
    @DisplayName("RedisTest")
    @Test
    void testString(){
        redisTemplate.opsForValue().set("name","Bob");
        //获取String数据
        Object name = redisTemplate.opsForValue().get("name");
        System.out.println("name = " + name);
    }

    @Test
    void testHash() {
        stringRedisTemplate.opsForHash().put("2","name","Alice");
        stringRedisTemplate.opsForHash().put("2","age","250");
        //获取
        Map<Object,Object> map = stringRedisTemplate.opsForHash().entries("1");
        System.out.println(map);
    }

    //    private static final ObjectMapper mapper = new ObjectMapper();
    //测试能不能存入对象
    @Test
    void testSavePost() throws JsonProcessingException {
        Post post = new Post();
        post.setUsername("Alice");
        post.setText("This is a test");
        //如果手动序列化就需要加入下面这行
//        String json = mapper.writeValueAsString(post);
        redisTemplate.opsForValue().set("name:100",post);
        Post name = (Post) redisTemplate.opsForValue().get("name:100");
        //手动反序列化
//        Post post2 = mapper.readValue(json,Post.class);
        System.out.println("name = " + name);
    }


    @Test
    void testClickLikes(){
        System.out.println(postService.clickLikes(42));
//        redisTemplate.opsForValue().set(pid +":likes", uid);
//        Object result = redisTemplate.opsForValue().get(pid +":likes");
//        System.out.println("result = "+ result);
    }

    @Test
    void testGetPostByPid(){
        Post post = postService.getPostByPid(42);
        System.out.println(post);
    }

    @Test
    void testLikeStatus(){
        Post post = postService.getPostByPid(42);
        System.out.println(postService.getLikeStatus(post));
        System.out.println("Test Successfully");
    }
}
