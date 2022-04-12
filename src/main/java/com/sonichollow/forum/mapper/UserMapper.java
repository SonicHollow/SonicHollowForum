package com.sonichollow.forum.mapper;

import com.sonichollow.forum.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * 用户模块的持久层接口
 */
@Component
@Mapper
public interface UserMapper {
    /**
     * 插入用户的数据
     * @param user 用户的数据
     * @return 受影响的行数 （增删改，可以根据返回值判断是否执行成功）
     */
    Integer insert(User user);

    /**
     * 根据用户名来查询用户的数据
     * @param username 用户名
     * @return 如果找到对于的用户，返回其数据，否则返回 null值
     */
    User findByUsername(String username);

    // Login Function

    @Select("select username from user where username=#{username} and password=#{password}")
    String isUser(String username, String password);

    @Select("select username from user where username=#{username}")
    String checkName(String username);

    @Insert("insert into user(username,email,password) values(#{username},#{email},#{password})")
    Integer addUser(User user);

    @Select("select * from user where username=#{username}")
    User getUser(String username);


    // MyInfo functions

