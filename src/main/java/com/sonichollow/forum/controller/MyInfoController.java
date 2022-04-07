package com.sonichollow.forum.controller;

import com.sonichollow.forum.entity.User;
import com.sonichollow.forum.mapper.UserMapper;
import com.sonichollow.forum.util.InfoForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class MyInfoController {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping(value = "myInfo/{username}")
    public String myInfo(Model model, @PathVariable String username) {
        User user = userMapper.getUser(username);
        model.addAttribute("user", user);
        model.addAttribute("infoForm", new InfoForm(username));
        return "myInfo";
    }


    @PostMapping(value = "/myInfoSetEmail")
    public String setEmail(Model model, @ModelAttribute InfoForm form) {
        User user = userMapper.getUser(form.getUser());

        System.out.println(form);
        if (user == null) {
            user = userMapper.getUser("Nyanner");
            System.out.println("~!!!!!!!!!!!!!!!!!~");
        }
        if (isEmail(form.getContent()))
            user.setEmail(form.getContent());
        model.addAttribute("user", user);
        model.addAttribute("infoForm", new InfoForm(user.getUsername()));
        return "myInfo";
    }

    @PostMapping(value = "/myInfoSetPhone")
    public String setPhone(Model model, @ModelAttribute InfoForm form) {
        User user = userMapper.getUser(form.getUser());

        System.out.println(form);
        if (user == null) {
            user = userMapper.getUser("Nyanner");
            System.out.println("~!!!!!!!!!!!!!!!!!~");
        }
        if (isPhone(form.getContent()))
            user.setPhone(form.getContent());
        model.addAttribute("user", user);
        model.addAttribute("infoForm", new InfoForm(user.getUsername()));
        return "myInfo";
    }

    @PostMapping(value = "/myInfoSetGender")
    public String setGender(Model model, @ModelAttribute InfoForm form) {
        User user = userMapper.getUser(form.getUser());

        System.out.println(form);
        if (user == null) {
            user = userMapper.getUser("Nyanner");
            System.out.println("~!!!!!!!!!!!!!!!!!~");
        }

        if (form.getContent().equalsIgnoreCase("female"))
            user.setGender(1);
        else if (form.getContent().equalsIgnoreCase("male"))
            user.setGender(0);

        model.addAttribute("user", user);
        model.addAttribute("infoForm", new InfoForm(user.getUsername()));
        return "myInfo";
    }

    @PostMapping(value = "/myInfoSetAvatar")
    public String setAvatar(Model model, @ModelAttribute InfoForm form) {
        User user = userMapper.getUser(form.getUser());

        System.out.println(form);
        if (user == null) {
            user = userMapper.getUser("Nyanner");
            System.out.println("~!!!!!!!!!!!!!!!!!~");
        }
        if (isURL(form.getContent()))
            user.setAvatar(form.getContent());

        model.addAttribute("user", user);
        model.addAttribute("infoForm", new InfoForm(user.getUsername()));
        return "myInfo";
    }


    private static boolean isEmail(String email) {
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private static boolean isPhone(String phone) {
        String regex = "^(\\+\\d{1,3}( )?)?((\\(\\d{1,3}\\))|\\d{1,3})[- .]?\\d{3,4}[- .]?\\d{4}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }

    private static boolean isURL(String url) {
        String regex = "(ht|f)tp(s?)\\:\\/\\/[0-9a-zA-Z]([-.\\w]*[0-9a-zA-Z])*(:(0-9)*)*(\\/?)([a-zA-Z0-9\\-\\.\\?\\,\\'\\/\\\\&%\\+\\$#_=]*)?";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(url);
        return matcher.matches();
    }




//    @RequestMapping("myInfo/{username}")
//    public String getUserName(@PathVariable String username) {
//        return userMapper.getUser(username).getUsername();
//    }

}
