package com.sonichollow.forum.controller;

import com.sonichollow.forum.service.ex.InsertException;
import com.sonichollow.forum.service.ex.ServiceException;
import com.sonichollow.forum.service.ex.UsernameDuplicatedException;
import com.sonichollow.forum.util.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 控制层，类的基类
 */
public class BaseController {
    /**
     * 操作成功的状态码
     */
    public static final int OK = 200;
    /**
     * 请求处理方法，自动将异常对象传递给此方法的参数列表上
     * 当项目中产生了异常，被统一拦截到此方法，充当请求处理方法
     *
     * @return 需要返回给前端的数据
     */
    @ExceptionHandler(ServiceException.class) //用于统一处理异常
    public JsonResult<Void> handleException(Throwable e) {
        JsonResult<Void> result = new JsonResult<>(e);
        if (e instanceof UsernameDuplicatedException) {
            result.setState(4000);
            result.setMessage("用户名已经被占用");
        } else if (e instanceof InsertException) {
            result.setState(5000);
            result.setMessage("注册时产生未知的异常");
        }
        return result;
    }
}
