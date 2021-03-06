package com.sonichollow.forum.controller;

import com.sonichollow.forum.entity.User;
import com.sonichollow.forum.mapper.UserMapper;
import com.sonichollow.forum.util.InfoForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class MyInfoController {

    @Autowired
    private UserMapper userMapper;

    final int One_DAY = 60 * 60 * 24 ;

    @RequestMapping(value = "myInfo/{username}")
    public String myInfo(Model model, @PathVariable String username) {
        if(Objects.equals(username, "null")){
            return "404";
        }
        User user = userMapper.getUser(username);
        model.addAttribute("user", user);
        model.addAttribute("infoForm", new InfoForm(username));
        return "myInfo";
    }

    @PostMapping(value = "myInfoSetName")
    public String setUsername(Model model, @ModelAttribute InfoForm form, HttpServletRequest request) {
        User user = userMapper.getUser(form.getUser());

        System.out.println(form);
        if (user == null)
            return "home";


        user.setUsername(form.getContent());

        this.updateUser(user);

        model.addAttribute("user", user);
        model.addAttribute("infoForm", new InfoForm(user.getUsername()));
        HttpSession session = request.getSession();
        session.setAttribute("username", user.getUsername());
        return "myInfo";
    }

    @PostMapping(value = "/myInfoSetEmail")
    public String setEmail(Model model, @ModelAttribute InfoForm form) {
        User user = userMapper.getUser(form.getUser());

        System.out.println(form);
        if (user == null)
            return "home";
        if (isEmail(form.getContent()))
            user.setEmail(form.getContent());

        this.updateUser(user);


        model.addAttribute("user", user);
        model.addAttribute("infoForm", new InfoForm(user.getUsername()));
        return "myInfo";
    }

    @PostMapping(value = "/myInfoSetPhone")
    public String setPhone(Model model, @ModelAttribute InfoForm form) {
        User user = userMapper.getUser(form.getUser());

        System.out.println(form);
        if (user == null)
            return "home";
        if (isPhone(form.getContent()))
            user.setPhone(form.getContent());

        this.updateUser(user);

        model.addAttribute("user", user);
        model.addAttribute("infoForm", new InfoForm(user.getUsername()));
        return "myInfo";
    }

    @PostMapping(value = "/myInfoSetGender")
    public String setGender(Model model, @ModelAttribute InfoForm form) {
        User user = userMapper.getUser(form.getUser());

        System.out.println(form);
        if (user == null)
            return "home";

        if (form.getContent().equalsIgnoreCase("female"))
            user.setGender(0);
        else if (form.getContent().equalsIgnoreCase("male"))
            user.setGender(1);

        this.updateUser(user);


        model.addAttribute("user", user);
        model.addAttribute("infoForm", new InfoForm(user.getUsername()));
        return "myInfo";
    }

    @PostMapping(value = "/myInfoSetAvatar")
    public String setAvatar(Model model, @ModelAttribute InfoForm form, HttpServletRequest req, HttpServletResponse resp) {
        User user = userMapper.getUser(form.getUser());

        System.out.println(form);
        if (user == null)
            return "home";
        if (isURL(form.getContent())) {
            String avatar = form.getContent();
            user.setAvatar(avatar);
            userMapper.updateUserAvatar(form.getContent(), user.getUid());
            HttpSession session = req.getSession(true);
            session.setAttribute("avatar", avatar);
            Cookie avatarCookie = new Cookie("avatar", avatar);
            avatarCookie.setPath("/");
            avatarCookie.setMaxAge(One_DAY);
            resp.addCookie(avatarCookie);
        }

        model.addAttribute("user", user);
        model.addAttribute("infoForm", new InfoForm(user.getUsername()));
        return "myInfo";
    }

    private void updateUser(User user) {
        userMapper.updateUser(
                user.getUsername(),
                user.getPassword(),
                user.getGender() == null?  0 : user.getGender(),
                user.getPhone(),
                user.getEmail(),
                user.getUid()
        );
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
}

