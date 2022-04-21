package com.sonichollow.forum.controller;
import com.sonichollow.forum.entity.User;
import com.sonichollow.forum.service.impl.CrudUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ManageUserController {
    @Autowired
    CrudUserServiceImpl crud;

    @GetMapping("manage_user")
    public String listAll(Model model) {
        List<User> users = crud.listAllAcc();
        model.addAttribute("listUsers", users);
        return "manage_user";
    }

    @GetMapping("insert_user")
    public String insert(){
        return "insert_user";
    }

    @PostMapping("insert_user")
    public String insertAcc(@RequestParam("username") String username,
                            @RequestParam("password") String password,
                            @RequestParam("gender") int gender,
                            @RequestParam("phone") String phone,
                            @RequestParam("email") String email) {
        crud.insertAcc(username, password, gender, phone, email);
        return "redirect:/manage_user";
    }

    @GetMapping("update_user/{uid}")
    public String update(@PathVariable("uid") int uid,
                         Model model){
        User user = crud.selectById(uid);
        model.addAttribute("user",user);
        return "update_user";
    }

    @PostMapping("update_user")
    public String update(@RequestParam("username") String username,
                         @RequestParam("password") String password,
                         @RequestParam("gender") int gender,
                         @RequestParam("phone") String phone,
                         @RequestParam("email") String email,
                         @RequestParam("uid") int uid
    ){
        crud.updateAcc(username,password,gender,phone,email,uid);
        return "redirect:/manage_user";
    }

    @RequestMapping("deleteUser/{uid}")
    public String delete(@PathVariable("uid")int uid){
        crud.deleteUserById(uid);
        return "redirect:/manage_user";
    }

}

