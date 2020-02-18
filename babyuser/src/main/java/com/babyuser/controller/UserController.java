package com.babyuser.controller;


import com.alibaba.fastjson.JSON;
import com.babyuser.service.user.*;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.common.Ip;
import com.common.MD5;
import com.pojo.*;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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

    @Resource
    private UserInfoService userInfoService;

    @Resource
    private BankService bankService;

    private MD5 md5;




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
            if (tUserAccount.getAccountType()==2){
                System.err.println(tUserAccount.getAccountType());
                map.put("type",2);
            }

            tLoginLog.setLoginResult(1);
            tUserAccount.setLastLoginTime(new Date());
            int num=userService.updateById(tUserAccount);        //修改登陆时间
/*            System.out.println("setLoginTime:"+tUserAccount.getLastLoginTime());*/
            String str=JSON.toJSONString(tUserAccount);
            map.put("code","200");
            map.put("data",str);
            UpdateWrapper<TUserAccount> updateWrapper=new UpdateWrapper<>();


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
        userWallet.setAvailableAmount(1000000);
        userWallet.setAccountId(rom);
        userWallet.setCreateTime(new Date());
        int NumUserWallet=userWalletService.insert(userWallet);


        TUserInfo tUserInfo=new TUserInfo();
        tUserInfo.setAccountId(rom);
        tUserInfo.setAvatar("nobody.jpg");
        tUserInfo.setCreateTime(new Date());
         int num =userInfoService.insert(tUserInfo);   //新增个人信息
        if (NumUser==1&&NumUserWallet==1&&num==1){
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
    public Object UserinfoWalletGet(@PathVariable("id") String id){
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

    /**
     * 修改头像
     * @param file
     * @param request
     * @return
     */
    @RequestMapping("/userinfo/uploadAvatar")
    @ResponseBody
    public  Object UploadAvatar(MultipartFile file, HttpServletRequest request){
        System.out.println("修改头像——————");
        Map<String,Object> map=new HashMap<>();
        String str="E:\\idea_Project\\baby_p2p\\babyuser\\src\\main\\resources\\static\\avatar";
        System.out.println("File:"+file.getOriginalFilename());
       /* Calendar currTime = Calendar.getInstance();
        String time = String.valueOf(currTime.get(Calendar.YEAR))+String.valueOf((currTime.get(Calendar.MONTH)+1));*/
        String path ="E:"+File.separator+"idea_Project"+File.separator+"baby_p2p"+File.separator+"babyuser"+File.separator+"src"+File.separator+"main"+File.separator+"resources"+File.separator+"static"+File.separator+"avatar"+File.separator;
        /*System.out.println("path:"+path);*/
       String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
       /* System.out.println("suffix:"+suffix);*/
        suffix = suffix.toLowerCase();
        /*System.out.println("suffix:"+suffix);*/
         if(suffix.equals(".jpg") || suffix.equals(".jpeg") || suffix.equals(".png") || suffix.equals(".gif")){
           /* String fileName = UUID.randomUUID().toString()+suffix;*/
             String fileName=file.getOriginalFilename();
/*             System.out.println("fileName:"+fileName);*/
            File targetFile = new File(path, fileName);
            /* System.out.println("targetFile.getParentFile():"+targetFile.getParentFile());*/
            if(!targetFile.getParentFile().exists()){   //注意，判断父级路径是否存在
                targetFile.getParentFile().mkdirs();
            }
            long size = 0;
            //保存
            try {
                file.transferTo(targetFile);
                size = file.getSize();
                map.put("code",200);
                map.put("data",file.getOriginalFilename());
            } catch (Exception e) {
                e.printStackTrace();
            }
           /* JSONObject result = new JSONObject();
            result.put("fileUrl", "/img/"+time+fileName);
            result.put("url", "/img/"+time+fileName);
            result.put("state", "SUCCESS");
            result.put("title", fileName);
            result.put("original", fileName);
            result.put("type", suffix);
            result.put("size", size);
            return result.toString();*/
        }else{
           /* JSONObject result = new JSONObject();
            result.put("ss", false);
            result.put("msg", "格式不支持");
            return result.toString();*/
             map.put("msg","上传失败！！！");

        }


       return map;
    }

    /**
     * 更改个人信息
     * @param accountId
     * @param avatar
     * @param realname
     * @param idCardNumber
     * @param phoneNumber
     * @param eduBackgroundId
     * @param incomeLevelId
     * @param marriageId
     * @param houseConditionId
     * @return
     */
    @RequestMapping("/userinfo/update")
    @ResponseBody
    public Object UserinfoUpdate(String accountId,String avatar,String realname,String idCardNumber,String phoneNumber
            ,Integer eduBackgroundId,Integer incomeLevelId,Integer marriageId,Integer houseConditionId) {
        System.out.println("点击修改按钮————————");
        int num = 0;
        int num1 = 0;
        Map<String, Object> map = new HashMap<>();
        QueryWrapper<TUserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account_id", accountId);
        TUserInfo tUserInfo = userInfoService.selectOne(queryWrapper);

        TUserInfo tUserInfo1 = new TUserInfo();     //需要修改的信息
        tUserInfo1.setAccountId(accountId);
        tUserInfo1.setAvatar(avatar);
        tUserInfo1.setCreateTime(new Date());
        tUserInfo1.setEduBackgroundId(eduBackgroundId);
        tUserInfo1.setHouseConditionId(houseConditionId);
        tUserInfo1.setIdCardNumber(idCardNumber);
        tUserInfo1.setIncomeLevelId(incomeLevelId);
        tUserInfo1.setMarriageId(marriageId);
        tUserInfo1.setPhoneNumber(phoneNumber);
        tUserInfo1.setRealname(realname);
        if (tUserInfo != null) {
            UpdateWrapper<TUserInfo> updateWrapper = new UpdateWrapper();
            updateWrapper.eq("account_id", accountId);
            num = userInfoService.update(tUserInfo1, updateWrapper);

            QueryWrapper<TUserAccount> queryWrapper1 = new QueryWrapper<>();
            queryWrapper1.eq("id", accountId);

            TUserAccount tUserAccount = userService.selectOne(queryWrapper1);
            if (tUserAccount.getFillUserinfo() == 0) {
                /* num =userInfoService.insert(tUserInfo1);   //新增个人信息*/
                QueryWrapper<TUserWallet> queryWrapper2=new QueryWrapper<>();
                queryWrapper2.eq("account_id",accountId);
                TUserWallet tUserWallet=userWalletService.selectOne(queryWrapper2);
                UpdateWrapper<TUserWallet> updateWrapper1 = new UpdateWrapper();
                updateWrapper1.set("credit_score",100).set("credit_line",100*100).set("residual_credit_line",100*100);
                updateWrapper1.eq("account_id", accountId);
                num1 = userWalletService.update(tUserWallet, updateWrapper1);       //首次修改设置100的信用分
                System.out.println("num1:" + num1);
            }


            if (num > 0 && num1 > 0) {
                map.put("code", 200);
                UpdateWrapper<TUserAccount> updateWrapper1=new UpdateWrapper<>();
                updateWrapper1.eq("id",accountId);
                TUserAccount tUserAccount1=new TUserAccount();
                tUserAccount1.setFillUserinfo(1);
                updateWrapper.eq("id",accountId);
                userService.update(tUserAccount1,updateWrapper1);
            }
            map.put("msg", "操作失败！！！");

        }
        return map;
    }

    /**
     * 显示个人信息
     * @param id
     * @return
     */
    @RequestMapping("/userinfo/get/{id}")
    @ResponseBody
    public Object UserinfoGet(@PathVariable("id") String id){
        System.out.println("userinfoid:"+id);
        QueryWrapper<TUserInfo> queryWrapper=new QueryWrapper<>();
        Map<String,Object> map=new HashMap<>();
        queryWrapper.eq("account_id",id);
        TUserInfo tUserInfo = userInfoService.selectOne(queryWrapper);
        if (tUserInfo!=null){

            map.put("data",tUserInfo);
        }
        map.put("code",200);
        map.put("msg","加载失败!!!");
        map.put("data",tUserInfo);
        return map;
    }

    @RequestMapping("bankcard/get/{id}")
    @ResponseBody
    public Object BankcardGet(@PathVariable("id") String id){
        System.out.println("bankcard:"+id);
        QueryWrapper<TBankCard> queryWrapper=new QueryWrapper<>();
        Map<String,Object> map=new HashMap<>();
        queryWrapper.eq("user_id",id);
        TBankCard tBankCard = bankService.selectOne(queryWrapper);
        if (tBankCard!=null){
            map.put("code",200);
            map.put("data",tBankCard);
        }else {
            map.put("code",404);
        }

        map.put("msg","加载失败!!!");
        return map;
    }

    @RequestMapping("bankcard/add")
    @ResponseBody
    public Object BankcardAdd(@PathParam("userId") String userId,@PathParam("realname") String realname,
                              @PathParam("cardNumber") String cardNumber,@PathParam("bankName") String bankName,
                              @PathParam("branchName") String branchName){
        /*System.out.println("bankcard:"+userId);*/
        String rom=RandomStringUtils.randomAlphanumeric(29); //生成随机id
        TBankCard tBankCard=new TBankCard();
        tBankCard.setBalance(1000000);
        tBankCard.setBankName(bankName);
        tBankCard.setBranchName(branchName);
        tBankCard.setCardNumber(cardNumber);
        tBankCard.setCreateTime(new Date());
        tBankCard.setId(rom);
        tBankCard.setRealname(realname);
        tBankCard.setUserId(userId);

        Map<String,Object> map=new HashMap<>();
        int num=bankService.insert(tBankCard);
        if (num>0){
            map.put("code",200);

        }

        map.put("msg","绑定失败!!!");
        return map;
    }



}
