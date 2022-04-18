package com.sonichollow.forum.controller;
import com.sonichollow.forum.entity.User;
import com.sonichollow.forum.service.impl.CrudImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ManageUserController {
    @Autowired
    CrudImpl crud;
//
//    @GetMapping("manage_user")
//    public String listAll(Model model) {
//        List<User> users = crud.listAllAcc();
//        System.out.println(users);
//        model.addAttribute("listUsers", users);
//        return "manage_user";
//    }

    @GetMapping("manage_user")
    public String listAll(Model model) {
        List<User> users = crud.listAllAcc();
        System.out.println(users);
        model.addAttribute("listUsers", users);
        return "manage_user";
    }

    @GetMapping("insert_page")
    public String insert(){
        return "insert_page";
    }

    @PostMapping("insert_page")
    public String insertAcc(@RequestParam("username") String username,
                            @RequestParam("password") String password,
                            @RequestParam("email") String email) {
        crud.insertAcc(username, password, email);
        return "manage_user";
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
                         @RequestParam("email") String email,
                         @RequestParam("uid") int uid
    ){
        crud.updateAcc(username,password,email,uid);
        return "manage_user";
    }

    @GetMapping("delete/{uid}")
    public String delete(@PathVariable("uid")int uid){
        crud.deleteUserById(uid);
        return "manage_user";
    }

}

