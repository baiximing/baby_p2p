package com.babyuser.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.websocket.server.PathParam;

@Controller
@RequestMapping("/user")
public class UserController {
    /**
     * 进入前台主页面
     * @return
     */
    @RequestMapping("/toIndex")
    public String ToIndex(){
        System.out.println("进入前台主页面——————————");
        return "index";
    }

    /**
     * 进入登陆页面
     * @return
     */
    @RequestMapping("/toLogin")
    public String ToLogin(){
        System.out.println("进入登陆页面——————————");
        return "login";
    }

    /**
     * 进入注册页面
     * @return
     */
    @RequestMapping("/toRegister")
    public String ToRegister(){
        System.out.println("进入注册页面——————————");
        return "register";
    }

    /**
     * 登陆
     * @return
     */
   /* @RequestMapping("login")
    @ResponseBody
    public  String Login(@PathParam("")){

        return "";
    }*/
}
