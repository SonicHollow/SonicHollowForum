package com.sonichollow.forum.controller;
import com.sonichollow.forum.dto.Result;
import com.sonichollow.forum.entity.Post;
import com.sonichollow.forum.entity.User;
import com.sonichollow.forum.mapper.UserMapper;
import com.sonichollow.forum.service.impl.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
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

    // Publish post
    @RequestMapping("publishPost")
    public ModelAndView posting(Post post, HttpServletRequest req){
        ModelAndView mv = new ModelAndView();
        String username = req.getSession().getAttribute("username").toString();
        String text = post.getText();
        post.setUsername(username);
        post.setText(text);
        post.setViewCount(0);
        int id = postService.PublishPost(post);
        System.out.println("Username: " + username + "  Text: " + text);
        System.out.println("Publish successfully!");
        mv.setViewName("redirect:publishedPost?pid="+id);
        return mv;
    }

    @RequestMapping(value = "postList")
    public String postList(Model model) {
        model.addAttribute("utils", PostUtil.getInstance());
        List<Post> posts = postService.list();
        Collections.reverse(posts);
        model.addAttribute("posts", posts);
        return "postList";
    }

    // Go to detail page
    @RequestMapping("/publishedPost")
    public String postDetailPage(int pid, Model model, HttpSession session){
        Integer sessionUid = (Integer) session.getAttribute("uid");
        // Get post by pid
        Post post = postService.getPostByPid(pid);
        User u = userMapper.findByUsername(post.getUsername());
        String avatar = u.getAvatar();
        // Get formatted time
        Date time = post.getCreatedTime();
        SimpleDateFormat sdf = new SimpleDateFormat("d MMM, yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        String post_time = sdf.format(time);

        // Check if the user likes the post
        boolean liked = false;
        if(sessionUid!=null){
            liked = postService.getLikeStatus(post);
        }
        // Add data to the model
        model.addAttribute("avatar", avatar);
        model.addAttribute("post_time", post_time);
        model.addAttribute("post",post);
        model.addAttribute("liked",liked);
        return "detail";
    }


    // Like the post
    @PutMapping("/like/{pid}")
    public Result likePost(@PathVariable("pid") int pid) {
        return postService.clickLikes(pid);
    }


    @RequestMapping("/likepost/{pid}")
    public String likeexample(@PathVariable("pid") int pid, Model model) {
        postService.clickLikes(pid);
        return "util/closeme";
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
