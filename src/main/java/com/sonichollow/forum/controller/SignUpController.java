package com.sonichollow.forum.controller;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.sonichollow.forum.entity.User;
import com.sonichollow.forum.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

@Controller
public class SignUpController {
    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private Producer captchaProducer;

    final int One_DAY = 60 * 60 * 24 ;

    @RequestMapping("signUpUser")
    public ModelAndView login(User user, String code, HttpServletRequest req, HttpServletResponse resp) {
        ModelAndView mv = new ModelAndView();
        // 获取Session中的验证码
        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        // 删除 Session中的验证码
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);
        String username=user.getUsername();
        String password=user.getPassword();
//        System.out.println("username " + username + " password " + password);
        // 检查 验证码是否正确
        // 验证码正确
        System.out.println(user);
        if (token != null && token.equalsIgnoreCase(code)) {
            // 检查用户是否存在
            // 用户存在
            if(userService.isUser(username,password)){
                mv.addObject("msg", "Username already taken!");
                mv.setViewName("redirect:regist");
            }
            // 用户不存在
            else{
                HttpSession session = req.getSession(true);
                session.setAttribute("username", username);
                Cookie nameCookie = new Cookie("username", username);
                Cookie passwordCookie = new Cookie("password", password);
                nameCookie.setPath("/");
                nameCookie.setMaxAge(One_DAY);
                passwordCookie.setPath("/");
                passwordCookie.setMaxAge(One_DAY);
                resp.addCookie(nameCookie);
                resp.addCookie(passwordCookie);
                //
                // default Avatar
                user.setAvatar("https://i.ibb.co/YWLvMPd/default-Avatar.jpg");
                userService.registerUser(user);
                mv.setViewName("redirect:myInfo/"+username);
            }
        }
        // 验证码错误
        else {
            // 把回显信息，保存到Request域中
            mv.addObject("msg", "Wrong verification code");
            mv.addObject("username", username);
            mv.addObject("password", password);
            mv.setViewName("redirect:regist");
        }
        return mv;
    }
}
