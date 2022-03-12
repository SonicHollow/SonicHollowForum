package com.sonichollow.forum.mapper;


import com.sonichollow.forum.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserMapperTests {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void insert() {
        User user = new User();
        user.setUsername("Tim");
        user.setPassword("123");
        Integer rows = userMapper.insert(user);
        System.out.println(rows);
    }

    /**
     * User{uid=1, username='Tim', password='123', salt='null', phone='null', email='null', gender=null, avatar='null', isDelete=null}
     */
    @Test
    public void findByUsername() {
        User user = userMapper.findByUsername("Tim");
        System.out.println(user);
    }

}
