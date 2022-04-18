package com.sonichollow.forum.service;

import com.sonichollow.forum.entity.User;

import java.util.List;

/**
 * 用户模块业务层接口
 */
public interface IUserService {
    /**
     * 用户注册方法
     * @param user 用户数据对象
     */
    void reg(User user);
    boolean isUser(String username,String password);
    //判断账号是否已经注册
    boolean checkName(String username);
    //注册成功后添加用户
    Integer addUser(User user);
    User getUser(String username);

}
