package com.sonichollow.forum.service.impl;
import com.sonichollow.forum.entity.User;
import com.sonichollow.forum.mapper.UserMapper;
import com.sonichollow.forum.service.Crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CrudImpl implements Crud {

    @Autowired
    UserMapper userMapper;

    @Override
    public User getAcc(String username, String password) {
        return userMapper.selectByNameAndAge(username,password);
    }

    @Override
    public void insertAcc(String username, String password, String email) {
        userMapper.insertUser(username,password,email);
    }

    @Override
    public void updateAcc(String username, String password, String email,int uid) {
        userMapper.updateUser(username, password, email, uid);
    }

    @Override
    public User selectById(int uid) {
        return userMapper.SelectById(uid);
    }

    @Override
    public void deleteUserById(int uid) {
        userMapper.deleteUser(uid);
    }

    @Override
    public List<User> listAllAcc() {
        return userMapper.selectAll();
    }

}
