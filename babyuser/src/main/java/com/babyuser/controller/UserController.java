package com.babyuser.controller;


import com.alibaba.fastjson.JSON;
import com.babyuser.service.user.UserService;
import com.babyuser.service.user.UserServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.common.MD5;
import com.dao.user.UserMapper;
import com.pojo.TUserAccount;
import org.json.JSONObject;
import org.json.JSONString;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.websocket.server.PathParam;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    private MD5 md5;

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
    @RequestMapping("login")
    @ResponseBody
    public  Object Login(@PathParam("username") String username,@PathParam("password") String password){
        Map<String,Object> map=new HashMap<>();

/*        System.out.println("username:"+username+"password:"+password);*/
        QueryWrapper<TUserAccount> queryWrapper=new QueryWrapper<>();

        queryWrapper.eq("username",username).eq("password",MD5.toMD5(password));
        TUserAccount tUserAccount=userService.selectOne(queryWrapper);
        if (tUserAccount!=null){
            String str=JSON.toJSONString(tUserAccount);
            map.put("code","200");
            map.put("data",str);

        }
        map.put("msg","登陆失败!!!");
        System.out.println("tUserAccount:"+tUserAccount);
        return map;
    }
}
