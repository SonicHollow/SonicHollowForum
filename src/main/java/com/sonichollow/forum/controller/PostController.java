package com.sonichollow.forum.controller;


import com.sonichollow.forum.entity.Post;
import com.sonichollow.forum.entity.User;
import com.sonichollow.forum.mapper.UserMapper;
import com.sonichollow.forum.service.impl.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller

public class PostController {
    @Autowired
    private PostServiceImpl postService;
    @Autowired
    private UserMapper userMapper;

    //去发帖页面
    @RequestMapping("/toPublish")
    public String toPublish(Model model, Post post){
        return "posting";
    }

    //发帖


    @RequestMapping("publishPost")
    public ModelAndView posting(Post post, HttpServletRequest req){
        ModelAndView mv = new ModelAndView();
        String username = req.getSession().getAttribute("username").toString();
        String text = post.getText();
        post.setUsername(username);
        post.setText(text);

        System.out.println("Username: " + username + "  Text: " + text);

        int id = postService.PublishPost(post);

        System.out.println("发布成功");
        mv.setViewName("redirect:publishedPost?pid="+id);
        return mv;
    }

    //去帖子详情页
    @RequestMapping("/publishedPost")
    public String postDetailPage(Model model,int pid){
        //注入内容需要更改
//        Integer sessionUid = (Integer) session.getAttribute("pid");
//        //获取帖子信息 浏览量 点赞信息
//        Post post = postService.getById(pid);
//        model.addAttribute("post",post);
        return "detail";
    }

}