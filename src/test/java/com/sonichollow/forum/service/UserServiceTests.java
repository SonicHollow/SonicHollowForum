package com.sonichollow.forum.service;


import com.sonichollow.forum.entity.User;
import com.sonichollow.forum.service.ex.ServiceException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTests {
    @Autowired
    private IUserService userService;

    @Test
    public void reg() {
        // Ctrl + Alt + T --- 6. try/catch
        try {
            User user = new User();
            user.setUsername("Alice");
            user.setPassword("123");//避免明文密码！一般解决方法，使用MD5 + 盐值 迭代三次
            userService.reg(user);
            System.out.println("注册成功");
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());//获取该异常类的对象，再获取其名称
            System.out.println(e.getMessage());//异常的具体描述信息
        }
    }

}
