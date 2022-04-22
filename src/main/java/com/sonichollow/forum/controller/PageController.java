package com.sonichollow.forum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
    @RequestMapping(value = "home")
    public String home() {
        return "home";
    }
    @RequestMapping(value = "manage_post")
    public String manage_post() {
        return "manage_post";
    }
    @RequestMapping(value = "manage_user")
    public String manage_user() {
        return "manage_user";
    }
    @RequestMapping(value = "index")
    public String index() {
        return "index";
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
    @RequestMapping(value = "posting")
    public String posting() {
        return "posting";
    }
    @RequestMapping(value = "login_success")
    public String login_success() {
        return "user/login_success";
    }
    @RequestMapping(value = "remind")
    public String remind() {
        return "user/remind";
    }
    @RequestMapping(value = "404")
    public String page_404 () {
        return "404";
    }
    @RequestMapping(value = "500")
    public String page_500 () {
        return "500";
    }
}
