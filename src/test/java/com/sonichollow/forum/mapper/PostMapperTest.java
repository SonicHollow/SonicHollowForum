package com.sonichollow.forum.mapper;

import com.sonichollow.forum.entity.Post;
import com.sonichollow.forum.entity.User;
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
public class PostMapperTest {
    @Autowired
    private PostMapper postMapper;

    @DisplayName("test mybatis-plus")
    @Test
    void contextLoads(){
        //查
        List<Post> post = postMapper.selectList(null);
        post.forEach(System.out::println);
        //update
        Post uppost = new Post();
        uppost.setUsername("Alice");
        uppost.setPostName("TestPost");
        uppost.setText("This is a post");

        int update = postMapper.update(uppost,null);
        System.out.println(update);
        //insert INSERT INTO post ( username, post_name, text ) VALUES ( ?, ?, ? )
        Post post3 = new Post();
        post3.setUsername("Bob");
        post3.setPostName("TestPost");
        post3.setText("This is a post");
        int result = postMapper.insert(post3);
        log.warn("insert => {}",result);
        log.warn("id => {}",post3.getPid());

        //delete
        //通过id删除用户信息 DELETE FROM post WHERE =?
        int delResult = postMapper.deleteById(5);
        log.warn("delResult => {}",delResult);


        //条件删除
        //DELETE FROM post WHERE text = ? AND username = ?
        Map<String,Object> map = new HashMap<>();
        map.put("username","Bob");
        map.put("text","This is a post");
        int result3 = postMapper.deleteByMap(map);
        log.warn("result3 => {}",result3);

        //通过多个id批量删除
        List<Integer> list= Arrays.asList(1,2,3);
        int result4 = postMapper.deleteBatchIds(list);
        log.warn("result4 => {}",result3);

    }

    @Test
    public void testUpdateViewCount() {
        int result = postMapper.updateViewCount(1);
        System.out.println("View Count ="+result);
    }

    @Test
    public void testGetALLByPid() {
        Post result = postMapper.getAllByPid(40);
        System.out.println(result);
    }
}
