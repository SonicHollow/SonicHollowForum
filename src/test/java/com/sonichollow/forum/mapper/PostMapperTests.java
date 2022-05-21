package com.sonichollow.forum.mapper;

import com.sonichollow.forum.entity.Post;
import com.sonichollow.forum.entity.User;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.*;

@SuppressWarnings("ALL")
@Slf4j
@SpringBootTest
//@Runwith(SpringRunner.class)
public class PostMapperTests {
    @Autowired
    private PostMapper postMapper;



    @Test
    void select() {
        List<Post> post = postMapper.selectList(null);
        post.forEach(System.out::println);
    }

//    @Test
//    void update() {
//        Post uppost = new Post();
//        uppost.setUsername("Alice");
//        uppost.setText("This is a post");
//        uppost.setViewCount(0);
//        int update = postMapper.update(uppost, null);
//        System.out.println(update);
//    }

    @Test
    void testInsert() {
        //insert INSERT INTO post ( username, post_name, text ) VALUES ( ?, ?, ? )
        Post post3 = new Post();
        post3.setUsername("Bob");
        post3.setText("This is a post");
        post3.setViewCount(0);
        int result = postMapper.insert(post3);
        log.warn("insert => {}", result);
        log.warn("id => {}", post3.getPid());
    }

    @Test
    void testDelete() {
        //delete user information by user id
        // DELETE FROM post WHERE =?
        int delResult = postMapper.deleteById(5);
        log.warn("delResult => {}", delResult);


        //DELETE FROM post WHERE text = ? AND username = ?
        Map<String, Object> map = new HashMap<>();
        map.put("username", "Bob");
        map.put("text", "This is a post");
        int result3 = postMapper.deleteByMap(map);
        log.warn("result3 => {}", result3);

        //delete by multiple id
        List<Integer> list = Arrays.asList(1, 2, 3);
        int result4 = postMapper.deleteBatchIds(list);
        log.warn("result4 => {}", result3);
    }

    @Test
    public void testUpdateViewCount() {
        int result = postMapper.updateViewCount(42);
        System.out.println("View Count =" + result);
    }

    @Test
    public void testGetALLByPid() {
        Post result = postMapper.getAllByPid(42);
        System.out.println(result);
    }

}