package com.sonichollow.forum.service.impl;

import com.sonichollow.forum.entity.User;
import com.sonichollow.forum.mapper.UserMapper;
import com.sonichollow.forum.service.IUserService;
import com.sonichollow.forum.service.ex.InsertException;
import com.sonichollow.forum.service.ex.UsernameDuplicatedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.*;

@Service //将当前类的对象交给 SpringBoot管理，自动创建对象以及对象维护
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    // Login
    @Override
    public boolean isUser(String username, String password) {
        return userMapper.isUser(username,password)!=null;
    }

    @Override
    public boolean checkName(String username) {
        return userMapper.checkName(username)==null;
    }

    @Override
    @Transactional
    public Integer registerUser(User user) {
        return userMapper.registerUser(user);
    }

    @Override
    @Transactional
    public Integer addUser(User user) {
        return userMapper.addUser(user);
    }

    @Override
    public User getUser(String username) {
        return userMapper.getUser(username);
    }


    @Override
    public void reg(User user) {
        String username = user.getUsername();
        //调用findByUsername(username)
        User result = userMapper.findByUsername(username);
        if (result != null) {
            //抛出用户名重复异常
            throw new UsernameDuplicatedException("Username exists");
        }

        // 密码加密，MD5
        // (串 + password + 串) ---> MD5加密，连续加载三次
        // 盐值 + password +盐值 ---> MD5加密

        String oldPassword = user.getPassword();
        // 获取盐值，随机生成字符串
        String salt = UUID.randomUUID().toString().toUpperCase();
        //将密码和盐值作为一个整体，进行加密处理
        String md5Password = getMD5Password(oldPassword, salt);

        user.setPassword(md5Password);
        user.setSalt(salt);

        //  insert 之前填好用户首次注册的信息
        //  补全数据：
        //  1. is_delete
        //  2. 4个日子，创建/更新时间和创建/更新者

        user.setIsDelete(0);
        user.setCreatedUser(user.getUsername());
        user.setModifiedUser(user.getUsername());
        Date date = new Date();
        user.setCreatedTime(date);
        user.setModifiedTime(date);


        //正常注册，执行注册业务功能(rows==1 插入数据库成功)
        Integer rows = userMapper.insert(user);
        if (rows != 1) {
            throw new InsertException("未知的注册错误");//数据库宕机...
        }
    }

    @Override
    public boolean isUser(String name, String password) {
        return userMapper.isUser(name,password)!=null;
    }

    @Override
    public boolean checkName(String name) {
        return userMapper.checkName(name)==null;
    }

    @Override
    @Transactional
    public Integer addUser(User user) {
        return userMapper.addUser(user);
    }

    @Override
    public User getUser(String name) {
        return userMapper.getUser(name);
    }

    /**
     * 密码加密
     *
     * @param password 密码
     * @param salt     盐值
     * @return 新密码MD5
     */

    private String getMD5Password(String password, String salt) {
        //加密三次
        for (int i = 0; i < 3; i++) {
            password = DigestUtils.md5DigestAsHex((salt + password + salt).getBytes()).toUpperCase();
        }
        return password;
    }


}
