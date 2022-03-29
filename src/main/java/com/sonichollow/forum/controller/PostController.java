package com.sonichollow.forum.controller;

import com.sonichollow.forum.entity.Post;
import com.sonichollow.forum.entity.User;
import com.sonichollow.forum.service.IPostService;
import com.sonichollow.forum.service.IUserService;
import com.sonichollow.forum.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController extends BaseController{
    @Autowired
    private IPostService postService;




//    发帖
    @RequestMapping("/publishPost")
    public String publishPost(Post post) {
        int id = postService.PublishPost(post);

        return "redirect:toPost.do?pid="+ id;
    }


    //   帖子详情页
    @RequestMapping("/toPost.do")
    public String toPost(int pid, Model model, HttpSession session){
        Integer sessionUid = (Integer) session.getAttribute("uid");
        //获取帖子信息
        Post post = postService.getPostByPid(pid);
        //获取评论信息

        //向模型中添加数据
        model.addAttribute("post",post);
        //添加评论
//        model.addAttribute("replyList",replyList);
        return "post";
    }
}
