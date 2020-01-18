package com.babyuser.controller;


import com.alibaba.fastjson.JSON;
import com.babyuser.service.user.UserLogService;
import com.babyuser.service.user.UserService;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.babyuser.service.user.UserWalletService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.common.Ip;
import com.common.MD5;
import com.dao.user.UserMapper;
import com.pojo.TLoginLog;
import com.pojo.TUserAccount;
import com.pojo.TUserWallet;
import net.minidev.json.writer.UpdaterMapper;
import org.apache.commons.lang.RandomStringUtils;
import org.json.JSONObject;
import org.json.JSONString;
import org.springframework.jdbc.object.UpdatableSqlQuery;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import java.util.HashMap;
import java.util.Map;



@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private UserLogService userLogService;

    @Resource
    private UserWalletService userWalletService;

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
     * 进入登陆日志页面
     * @return
     */
    @RequestMapping("/toLoginLog")
    public String toLoginLog(){
        System.out.println("进入账户充值页面——————————");
        return "loginlog";
    }

    /**
     * 进入账户充值页面
     * @return
     */
    @RequestMapping("/toRecharge")
    public String toRecharge(){
        System.out.println("进入账户充值页面——————————");
        return "recharge";
    }

    /**
     * 进入个人信息页面
     * @return
     */
    @RequestMapping("/toPersonal")
    public String toPersonal(){
        System.out.println("进入个人信息页面——————————");
        return "personal";
    }

    /**
     * 进入设置个人信息页面
     * @return
     */
    @RequestMapping("/toUserinfo")
    public String toUserinfo(){
        System.out.println("进入个人信息页面——————————");
        return "userinfo";
    }
    /**
     * 登陆
     * @return
     */
    @RequestMapping("login")
    @ResponseBody
    public  Object Login(@PathParam("username") String username, @PathParam("password") String password
            , @PathParam("accountType") Integer accountType, HttpServletRequest request){
        Map<String,Object> map=new HashMap<>();
        SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS" );

/*        System.out.println("username:"+username+"password:"+password);*/
        QueryWrapper<TUserAccount> queryWrapper=new QueryWrapper<>();

        queryWrapper.eq("username",username).eq("password",MD5.toMD5(password));
        TUserAccount tUserAccount=(TUserAccount) userService.selectOne(queryWrapper);    //查询方法
        TLoginLog tLoginLog=new TLoginLog();
        tLoginLog.setLoginTime(new Date());
        tLoginLog.setUsername(username);
        tLoginLog.setAccountType(accountType);
        tLoginLog.setCreateTime(new Date());
        tLoginLog.setLoginResult(0);
        tLoginLog.setIp(Ip.getIp(request));
        if (tUserAccount!=null){
            tLoginLog.setLoginResult(1);
/*            System.out.println("setLoginTime:"+tUserAccount.getLastLoginTime());*/
            String str=JSON.toJSONString(tUserAccount);
            map.put("code","200");
            map.put("data",str);
            UpdateWrapper<TUserAccount> updateWrapper=new UpdateWrapper<>();
            tUserAccount.setLastLoginTime(new Date());
            int num=userService.updateById(tUserAccount);        //修改登陆时间
        }
        map.put("msg","登陆失败!!!");

        int numInsert=userLogService.insert(tLoginLog);   //新增日志
        return map;
    }

    /**
     * 注册请求
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("/register")
    @ResponseBody
    public  Object Register(@PathParam("username") String username,@PathParam("password") String password){
        String rom=RandomStringUtils.randomAlphanumeric(29); //生成随机id
        Map<String,Object> map=new HashMap<>();
        TUserAccount userAccount=new TUserAccount();   //创建账户
        userAccount.setUsername(username);
        userAccount.setPassword(MD5.toMD5(password));
        userAccount.setAccountStatus(1);
        userAccount.setAccountType(1);
        userAccount.setFillUserinfo(0);
        userAccount.setCreateTime(new Date());
        userAccount.setId(rom);
        int NumUser=userService.insert(userAccount);

        TUserWallet userWallet=new TUserWallet();     //送金额
        userWallet.setAvailableAmount(10000);
        userWallet.setAccountId(rom);
        userWallet.setCreateTime(new Date());
        int NumUserWallet=userWalletService.insert(userWallet);

        if (NumUser==1&&NumUserWallet==1){
            map.put("code","200");
        }
        map.put("msg","登陆失败!!!");
        return map;
    }

    /**
     * 判断用户name是否存在
     * @param username
     * @return
     */
    @RequestMapping("/checkUsername")
    @ResponseBody
    public Object checkUsername(String username) {
        QueryWrapper<TUserAccount> queryWrapper=new QueryWrapper();
        Map<String,Object> map=new HashMap<>();
        queryWrapper.eq("username",username);
        TUserAccount userAccount=userService.selectOne(queryWrapper);
        if (userAccount!=null){
            return false;
        }

        return true;
    }

    @RequestMapping("/wallet/get/{id}")
    @ResponseBody
    public Object userinfoGet(@PathVariable("id") String id){
        System.out.println("id:"+id);
        QueryWrapper<TUserWallet> queryWrapper=new QueryWrapper<>();
        Map<String,Object> map=new HashMap<>();
        queryWrapper.eq("account_id",id);
        TUserWallet tUserWallet = userWalletService.selectOne(queryWrapper);
        if (tUserWallet!=null){
            map.put("code",200);
            map.put("data",tUserWallet);
            System.out.println("tUserWallet"+tUserWallet.getAvailableAmount());
        }
        map.put("msg","加载失败!!!");
        return map;
    }
}
