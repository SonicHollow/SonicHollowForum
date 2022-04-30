package com.sonichollow.forum.controller;
import com.google.code.kaptcha.Constants;
import com.sonichollow.forum.entity.User;
import com.sonichollow.forum.mapper.UserMapper;
import com.sonichollow.forum.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;

import com.google.code.kaptcha.Producer;
import org.springframework.web.servlet.ModelAndView;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

@Controller
public class LoginController {
    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private Producer captchaProducer;

    final int One_DAY = 60 * 60 * 24 ;

    @RequestMapping("loginimage")
    public void getKaptchaImage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");
        //生成验证码
        String capText = captchaProducer.createText();
        session.setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);
        //向客户端写出
        BufferedImage bi = captchaProducer.createImage(capText);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(bi, "jpg", out);
        try {
            out.flush();
        } finally {
            out.close();
        }
    }

    @RequestMapping("avatar")
    public void getAvatar(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        System.out.println("username " + username);
        User user = userMapper.getUser(username);
        String avatar = user.getAvatar();
        model.addAttribute("avatar", avatar);
    }

    @RequestMapping("loginUser")
    public ModelAndView login(User user, String code, HttpServletRequest req, HttpServletResponse resp) {
        ModelAndView mv = new ModelAndView();
        // 获取Session中的验证码
        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        // 删除 Session中的验证码
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);
        String username=user.getUsername();
        String password=user.getPassword();

        User userGet = userMapper.getUser(username);
        String avatar = userGet.getAvatar();

        // 检查 验证码是否正确
        // 验证码正确
        if (token != null && token.equalsIgnoreCase(code)) {
            // 检查用户是否存在
            // 用户存在
            if(userService.isUser(username,password)){
                // 保存登录信息
                HttpSession session = req.getSession(true);
                session.setAttribute("username", username);
                session.setAttribute("avatar", avatar);
                Cookie nameCookie = new Cookie("username", username);
                Cookie passwordCookie = new Cookie("password", password);
                Cookie avatarCookie = new Cookie("avatar", avatar);
                nameCookie.setPath("/");
                nameCookie.setMaxAge(One_DAY);
                passwordCookie.setPath("/");
                passwordCookie.setMaxAge(One_DAY);
                avatarCookie.setPath("/");
                avatarCookie.setMaxAge(One_DAY);
                resp.addCookie(nameCookie);
                resp.addCookie(passwordCookie);
                resp.addCookie(avatarCookie);
                mv.setViewName("redirect:login_success");
            }
            // 用户不存在
            else{
                mv.addObject("msg", "Wrong username or password!");
                mv.setViewName("redirect:login");
            }
        }
        // 验证码错误
        else {
            // 把回显信息，保存到Request域中
            mv.addObject("msg", "Wrong verification code");
            mv.addObject("username", username);
            mv.addObject("password", password);
            mv.addObject("avatar", avatar);
            mv.setViewName("redirect:login");
        }
        return mv;
    }

    @RequestMapping("signOut")
    public String signOut(HttpServletRequest req,HttpServletResponse resp){
        //删除session域中的信息
        req.getSession().removeAttribute("username");
        req.getSession().removeAttribute("avatar");
        //删除cookie信息
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if ("username".equals(c.getName())) {
                    c.setMaxAge(0);
                    resp.addCookie(c);
                }
                if ("password".equals(c.getName())) {
                    c.setMaxAge(0);
                    resp.addCookie(c);
                }
                if ("avatar".equals(c.getName())) {
                    c.setMaxAge(0);
                    resp.addCookie(c);
                }
            }
        }
        return "redirect:index";
    }
}
