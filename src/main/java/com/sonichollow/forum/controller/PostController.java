package com.sonichollow.forum.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.sonichollow.forum.dto.Result;
import com.sonichollow.forum.entity.Post;
import com.sonichollow.forum.entity.User;
import com.sonichollow.forum.mapper.UserMapper;
import com.sonichollow.forum.service.impl.PostServiceImpl;
import com.sonichollow.forum.util.SearchForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.criteria.Fetch;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller

public class PostController {
    @Autowired
    private PostServiceImpl postService;
    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/toPublish")
    public String toPublish(Model model, Post post) {
        return "posting";
    }

    // Publish post

    @RequestMapping("publishPost")
    public ModelAndView posting(Post post, HttpServletRequest req) {
        ModelAndView mv = new ModelAndView();
        String username = req.getSession().getAttribute("username").toString();
        String text = post.getText();
        post.setUsername(username);
        post.setText(text);
        System.out.println("Username: " + username + "  Text: " + text);
        int id = postService.PublishPost(post);
        System.out.println("发布成功");
        mv.setViewName("redirect:publishedPost?pid=" + id);
        return mv;
    }

    // Go to detail page
    @RequestMapping("/publishedPost")
    public String postDetailPage(int pid, Model model, HttpSession session, HttpServletRequest req) {
        String username = req.getSession().getAttribute("username").toString();
        User view_user = userMapper.findByUsername(username);
        Integer sessionUid = (Integer) session.getAttribute("uid");
        // Get post by pid
        Post post = postService.getPostByPid(pid);
        User post_user = userMapper.findByUsername(post.getUsername());
        String avatar = post_user.getAvatar();
        // Get formatted time
        Date time = post.getCreatedTime();
        SimpleDateFormat sdf = new SimpleDateFormat("d MMM, yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        String post_time = sdf.format(time);
        // Check if the user likes the post
        boolean liked = false;
        if (sessionUid != null) {
            liked = postService.getLikeStatus(post, view_user.getUsername());
        }
        Integer likes = postService.getLikesFromPid(pid);
        System.out.println("likes: " + likes);
        System.out.println("is_like: " + liked);
        // Add data to the model
        model.addAttribute("avatar", avatar);
        model.addAttribute("post_time", post_time);
        model.addAttribute("post", post);
        model.addAttribute("likes", likes);
        model.addAttribute("liked", liked);
        model.addAttribute("view_user", view_user);
        return "detail";
    }

    // Like the post
//    @RequestMapping("/like/{pid}/{uid}")
//    public Result likePost(@PathVariable("pid") int pid , @PathVariable("uid") int uid) {
//        return postService.clickLikes(pid, uid);
//    }

    @RequestMapping("/likepost/{pid}/{uid}")
    public String likeexample(@PathVariable("pid") int pid, @PathVariable("uid") int uid) {
        postService.clickLikes(pid, uid);
        return "redirect:/publishedPost?pid=" + pid;
    }

    @RequestMapping(value = "postList")
    public String postList(Model model, HttpServletRequest req) {
        String username = req.getSession().getAttribute("username").toString();
        User user = userMapper.findByUsername(username);
        model.addAttribute("user", user);
        model.addAttribute("utils", PostUtil.getInstance());
        List<Post> posts = postService.list();
        Collections.reverse(posts);
        model.addAttribute("posts", posts);
        model.addAttribute("searchForm", new SearchForm("", "Time"));
        return "postList";
    }

    // searchPost
    @RequestMapping(value = "searchPost")
    public String searchPost(Model model, @ModelAttribute SearchForm searchForm, HttpServletRequest req) {
        String username = req.getSession().getAttribute("username").toString();
        User user = userMapper.findByUsername(username);
        model.addAttribute("user", user);
        model.addAttribute("utils", PostUtil.getInstance());
        LambdaQueryWrapper<Post> wrapper = Wrappers.lambdaQuery();
        if (searchForm.getSearchKey() != null && !searchForm.getSearchKey().isEmpty())
            wrapper.like(Post::getText, searchForm.getSearchKey());
        List<Post> queryResult = postService.getBaseMapper().selectList(wrapper);

        Collections.reverse(queryResult);
        model.addAttribute("posts", queryResult);
        model.addAttribute("searchForm", searchForm);


        return "postList";
    }
    //sortPost
    @RequestMapping(value = "sortPost")
    public String sortPost(Model model, @RequestParam String option, HttpServletRequest req) {
        String username = req.getSession().getAttribute("username").toString();
        User user = userMapper.findByUsername(username);
        model.addAttribute("user", user);
        String sortOpt = "Time ";
        String searchKey = "";
        if (option != null && option.length() >= 5) {
            sortOpt = option.substring(0, 5);
            searchKey = option.substring(5);
        }
        LambdaQueryWrapper<Post> wrapper = Wrappers.lambdaQuery();
        if (searchKey != null && !searchKey.isEmpty())
            wrapper.like(Post::getText, searchKey);
        List<Post> posts = postService.getBaseMapper().selectList(wrapper);

        Collections.reverse(posts);
        switch (sortOpt){
            case "Time ":
                posts.sort((o1, o2) -> o2.getCreatedTime().compareTo(o1.getCreatedTime()));
                break;
            case "Views":
                posts.sort((o1, o2) -> o2.getViewCount().compareTo(o1.getViewCount()));
                break;
            case "Likes":
                posts.sort((o1, o2) -> o2.getLikes().compareTo(o1.getLikes()));
                break;
        }

        model.addAttribute("utils", PostUtil.getInstance());
        model.addAttribute("posts", posts);
        model.addAttribute("searchForm", new SearchForm(searchKey, sortOpt));
        return "postList";
    }


    // Util class singleton
    public static class PostUtil {
        private static PostUtil instance;

        public static PostUtil getInstance() {
            if (instance == null) instance = new PostUtil();
            return instance;
        }

        public String monthToString(int month) {
            String[] months = {
                    "JAN", "FEB", "MAR", "APR", "MAY", "JUN",
                    "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"
            };
            if (month >= 0 || month < 12)
                return months[month];
            else return "ERR";
        }
    }

}