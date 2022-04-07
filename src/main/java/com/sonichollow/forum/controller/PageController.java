package com.sonichollow.forum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
    @RequestMapping(value = "home")
    public String home() {
        return "home";
    }
    @RequestMapping(value = "index")
    public String index() {
        return "index";
    }
    @RequestMapping(value = "about")
    public String about() {
        return "about";
    }
    @RequestMapping(value = "detail")
    public String detail() {
        return "detail";
    }
    @RequestMapping(value = "login")
    public String login() {
        return "user/login";
    }
    @RequestMapping(value = "regist")
    public String regist() {
        return "user/regist";
    }
    @RequestMapping(value = "templates")
    public String templates() {
        return "templates";
    }
    @RequestMapping(value = "postList")
    public String postList() {
        return "postList";
    }
    @RequestMapping(value = "posting")
    public String posting() {
        return "posting";
    }
    @RequestMapping(value = "login_success")
    public String login_success() {
        return "user/login_success";
    }
    @RequestMapping(value = "regist_success")
    public String regist_success() {
        return "user/regist_success";
    }
    @RequestMapping(value = "remind")
    public String remind() {
        return "user/remind";
    }

}
