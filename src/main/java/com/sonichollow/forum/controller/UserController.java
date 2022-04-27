package com.sonichollow.forum.controller;

import com.google.code.kaptcha.Constants;
import com.sonichollow.forum.entity.User;
import com.sonichollow.forum.service.IUserService;
import com.sonichollow.forum.service.impl.UserServiceImpl;
import com.sonichollow.forum.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@Controller
@RestController //@Controller+@ResponseBody
@RequestMapping("users")
public class UserController extends BaseController {
    @Autowired
    private IUserService userService;

    @RequestMapping("reg")
    // @ResponseBody //表示此方法响应以Json格式进行数据给前端
    public JsonResult<Void> reg(User user) {
        userService.reg(user);
        return new JsonResult<Void>(OK);
    }
}

// http://localhost:8080/users/reg?username=Tom&password=123456


//        //抽象到父类BaseController，简化代码
//        //创建响应结果对象
//        JsonResult<Void> result=new JsonResult<>();
//
//        try {
//            userService.reg(user);
//            result.setState(200);//正常
//            result.setMessage("用户名注册成功");
//        } catch (UsernameDuplicatedException e) {
//            result.setState(400);
//            result.setMessage("用户名已存在");
//        } catch (InsertException e){
//            result.setState(5000);
//            result.setMessage("注册时产生未知的异常");
//        }