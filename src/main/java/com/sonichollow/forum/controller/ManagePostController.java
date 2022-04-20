package com.sonichollow.forum.controller;
import com.sonichollow.forum.entity.Post;
import com.sonichollow.forum.entity.User;
import com.sonichollow.forum.service.impl.CrudPostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ManagePostController {
    @Autowired
    CrudPostServiceImpl crud;

    @GetMapping("manage_post")
    public String listAll(Model model) {
        List<Post> posts = crud.listAllAccp();
        model.addAttribute("listPosts", posts);
        return "manage_post";
    }

    @GetMapping("delete/{pid}")
    public String delete(@PathVariable("pid")int pid){
        crud.deletepostById(pid);
        return "redirect:/manage_post";
    }
}
