package com.babyuser.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
    /**
     * 进入前台主页面
     * @return
     */
    @RequestMapping("/index")
    public String ToIndex(){
        System.out.println("进入前台主页面——————————");
        return "index";
    }

    /**
     * 进入登陆页面
     * @return
     */
    @RequestMapping("/login")
    public String ToLogin(){
        System.out.println("进入登陆页面——————————");
        return "login";
    }

    /**
     * 进入注册页面
     * @return
     */
    @RequestMapping("/register")
    public String ToRegister(){
        System.out.println("进入注册页面——————————");
        return "register";
    }
}
