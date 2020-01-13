package com.babyuser.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
    @RequestMapping("/index")
    public String Index(){
        System.out.println("进入前台主页面——————————");
        return "index";
    }
}
