package com.sonichollow.forum.controller;

import com.sonichollow.forum.entity.User;
import com.sonichollow.forum.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Controller
public class UserInfoController {
    @Autowired
    private UserMapper userMapper;

    @RequestMapping(value = "userInfo/{username}")
    public String userInfo(Model model, @PathVariable String username) {
        if(Objects.equals(username, "null")){
            return "404";
        }
        User user = userMapper.getUser(username);
        model.addAttribute("user", user);
        return "userInfo";
    }


}
