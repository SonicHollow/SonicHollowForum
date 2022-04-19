package com.sonichollow.forum.controller;


import com.sonichollow.forum.dto.Result;
import com.sonichollow.forum.entity.Post;
import com.sonichollow.forum.service.impl.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller

public class PostController {
    @Autowired
    private PostServiceImpl postService;


    //去发帖页面
    @RequestMapping("/toPublish")
    public String toPublish(Model model, Post post){
        return "publish";
    }

    //发帖

    /**
     *
     * @param model
     * @param post
     * @return
     */
    @RequestMapping("/Publish")
    public String publish(Model model, Post post){
        int id = postService.PublishPost(post);
        return "redirect:publishedPost?pid"+id;
    }

    //去帖子详情页
    @RequestMapping("/publishedPost")
    public String postDetailPage(int pid,Model model,HttpSession session){
        Integer sessionUid = (Integer) session.getAttribute("uid");
//        //获取帖子信息
        Post post = postService.getPostByPid(pid);
        //判断用户是否已经点赞
        boolean liked = false;
        if(sessionUid!=null){
            liked = postService.getLikeStatus(post);
        }
//        //向模型中添加数据
        model.addAttribute("post",post);
        model.addAttribute("liked",liked);
        return "postPage";
    }


    //点赞
    @PutMapping("/like/{pid}")
    public Result likePost(@PathVariable("pid") int pid) {
        return postService.clickLikes(pid);
    }
}
