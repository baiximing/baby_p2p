package com.babyrepayment.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.babyrepayment.service.AccountFlow.AccountFlowService;
import com.babyrepayment.service.Repayment.RepaymentService;

import com.babyrepayment.service.User.UserWalletService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pojo.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.util.*;

@CrossOrigin
@Controller
@RequestMapping("/finance")
public class RspaymentController {

    @Resource
    public RepaymentService repaymentService;

    @Resource
    private UserWalletService userWalletService;

    @Resource
    private AccountFlowService accountFlowService;

    /**
     * 还款明细查询
     * @param queryObject
     * @return
     */
    @RequestMapping("repayment/query")
    @ResponseBody
    public Object RepaymentGetByBorrowId(QueryObject queryObject) {
        Map<String,Object> map=new HashMap<>();
        QueryWrapper<TRepayment> queryWrapper=new QueryWrapper();
        queryWrapper.eq("borrow_user_id",queryObject.getBorrowUserId());
        if (queryObject.getBeginDate()!=null&&queryObject.getEndDate()!=null){
            queryWrapper.between("deadline",queryObject.getBeginDate(),queryObject.getEndDate());
        }

        if (queryObject.getState()!=null&&Integer.parseInt(queryObject.getState()) > 0) {
            queryWrapper.eq("state",queryObject.getState());
        }

        Page<TRepayment> page=new Page<>(Long.parseLong(queryObject.getCurrentPage()),3);
        if (queryObject.getCurrentPage()!=null){
              page.setCurrent(Long.parseLong(queryObject.getCurrentPage()));
        }
        IPage<TRepayment> iPage=repaymentService.selectPage(page,queryWrapper);
        System.out.println("总页数："+iPage.getPages());
        System.out.println("总记录数："+iPage.getTotal());
        iPage.getRecords().forEach(System.out::println);
        if (iPage!=null){
            map.put("code",200);
            String str=JSON.toJSONString(iPage);
            map.put("data",iPage);
        }
        return map;

    }

    /**
     * 还款管理
     * @param id
     * @param userId
     * @return
     */
    @RequestMapping("repayment/repay")
    @ResponseBody
    public Object repaymentRepay(String id,String userId){
        List<TRepaymentDetail> list =new ArrayList<>();
        Map<String,Object> map=new HashMap<>();
        DecimalFormat decimalFormat =new DecimalFormat("0.00");//设置保留位数

        //new实体类
        TUserWallet userWallet =new TUserWallet();
        TRepayment tRepayment =new TRepayment();
        TAccountFlow accountFlow =new TAccountFlow();

        QueryWrapper<TUserWallet> queryWrapper=new QueryWrapper<>();
        queryWrapper.gt("available_amount",0).eq("account_id",userId);
        userWallet=userWalletService.selectOne(queryWrapper);
        if (userWallet.getAvailableAmount()>=userWallet.getRepaidAmount()&&userWallet!=null){//判断账户是否有足够的金额还款
            UpdateWrapper<TUserWallet> walletWrapper=new UpdateWrapper<>();
            //修改还款表人数据
            walletWrapper.eq("account_id",userId)
                    //用户可用金额减还款金额
                    .set("available_amount",userWallet.getAvailableAmount()-userWallet.getRepaidAmount())
                    //用户信用得分
                    .set("credit_score",userWallet.getCreditScore()+1)
                    //用户授信额度加10000分（100块）
                    .set("credit_line",userWallet.getCreditLine()+10000);
            int num1=userWalletService.update(null,walletWrapper);

            //修改还款表数据
            UpdateWrapper<TRepayment> repaymentWrapper=new UpdateWrapper<>();
            repaymentWrapper.eq("id",id).eq("borrow_user_id",userId)
                    //修改还款状态,修改时间
                    .set("state",3).set("repayment_time",new Date());
            int num2=repaymentService.update(null,repaymentWrapper);

            QueryWrapper<TRepayment> TrepaymentWrapper=new QueryWrapper<>();
            TrepaymentWrapper.eq("borrow_user_id",userId).eq("id",id);
            tRepayment= repaymentService.selectOne(TrepaymentWrapper);
            //流水用户id
            accountFlow.setAccountId(userId);
            //流水金额
            accountFlow.setAmount(tRepayment.getTotalAmount());
            //资金流水类型（30还款）
            accountFlow.setFlowType(30);
            //可用金额
            accountFlow.setAvailableAmount(userWallet.getAvailableAmount());

            //冻结金额
            accountFlow.setFreezeAmount(userWallet.getFreezeAmount());
            //流水说明
            accountFlow.setRemark("还款"+tRepayment.getBorrowTitle()+"成功，还款金额："+tRepayment.getTotalAmount()+"元");
            //创建日期
            accountFlow.setCreateTime(new Date());
            //插入一条账号流水
            int num3 =accountFlowService.insert(accountFlow);
            if (num1>0&&num2>0&&num3>0){
                map.put("code",200);
            }else {
                map.put("msg","失败!!!");
            }
        }else {
            map.put("msg","余额不足请充值!!!");
        }
        System.out.println(userWallet.getAccountId());
        return map;
    }
}
