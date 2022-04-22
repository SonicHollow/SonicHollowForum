package com.sonichollow.forum.service;
import com.sonichollow.forum.entity.User;

import java.util.List;

public interface CrudService {

    //登录用户
    User getAcc(String username, String password);

    //查看所有用户
    List<User> listAllAcc();

    //注册，新建用户
    void insertAcc(String username, String password, int gender, String phone, String email);

    //修改用户
    void updateAcc(String username, String password, int gender, String phone, String email, int uid);

    //按照id查找用户
    User selectById(int uid);

    //根据id删除用户
    void deleteUserById(int uid);

}

