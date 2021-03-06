package com.sonichollow.forum.service.impl;
import com.sonichollow.forum.entity.User;
import com.sonichollow.forum.mapper.UserMapper;
import com.sonichollow.forum.service.CrudService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CrudUserServiceImpl implements CrudService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User getAcc(String username, String password) {
        return userMapper.selectByUsernameAndPassword(username,password);
    }

    @Override
    public void insertAcc(String username, String password, int gender, String phone, String email, String avatar) {
        userMapper.insertUser(username,password,gender,phone,email, avatar);
    }

    @Override
    public void updateAcc(String username, String password, int gender, String phone, String email, int uid) {
        userMapper.updateUser(username, password, gender, phone, email, uid);
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
