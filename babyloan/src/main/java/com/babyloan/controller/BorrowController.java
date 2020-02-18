package com.babyloan.controller;

import com.alibaba.fastjson.JSON;
import com.babyloan.service.Borrow.BorrowService;
import com.babyloan.service.User.UserWalletService;
import com.babyloan.util.BigDecimalUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pojo.QueryObject;
import com.pojo.TBorrow;
import com.pojo.TUserWallet;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.websocket.server.PathParam;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@Controller
@RequestMapping("/finance")
public class BorrowController {

    @Resource
    public BorrowService borrowService;
    @Resource
    public UserWalletService userWalletService;
    /**
     * 信用借款
     * @return
     */
    @RequestMapping("borrow/add")
    @ResponseBody
    public Object borrowadd(@PathParam("showBorrowAmount")long showBorrowAmount,
                            @PathParam("yearRate")long yearRate,
                            @PathParam("repaymentMonth")long repaymentMonth,
                            @PathParam("repaymentType")long repaymentType,
                            @PathParam("bidDays")long bidDays,
                            @PathParam("title")String title,
                            @PathParam("description")String description,
                            @PathParam("borrowUserId")String borrowUserId,
                            @PathParam("borrowUsername")String borrowUsername){
        TUserWallet tUserWallet=null;
        TBorrow tBorrow =new TBorrow();
        String rom=RandomStringUtils.randomAlphanumeric(29);
        //借款状态（具体状态不清楚。1为借款）
        tBorrow.setBorrowState(10);
        //借款类型( 1.信用贷 2.车贷 3.房贷 )
        tBorrow.setBorrowType(1);
        //已投标数量(当前有几个人已投资)
        tBorrow.setBidNum(0);//创建时为0
        tBorrow.setRepaymentType(repaymentType);
        tBorrow.setBorrowAmount(showBorrowAmount);
        tBorrow.setYearRate(yearRate);
        tBorrow.setRepaymentMonth(repaymentMonth);
        tBorrow.setBidDays(bidDays);
        tBorrow.setTitle(title);
        tBorrow.setDescription(description);
        tBorrow.setId(rom);
        tBorrow.setBorrowUserId(borrowUserId);
        tBorrow.setBorrowUsername(borrowUsername);
        //申请时间
        tBorrow.setApplyTime(new Date());
        //创建时间
        tBorrow.setCreateTime(new Date());
        //当前已投标金额(单位：分)
        tBorrow.setCurrentBidAmount(0l);
        //当前已投标利息(单位：分)
        tBorrow.setCurrentBidInterest(0l);
        //招标截止时间
        tBorrow.setBidDeadline(new Date());
        //发标时间
        tBorrow.setPublishTime(new Date());
        //总利息
        Double gInterest=0.0;
        //等额本息
        if (tBorrow.getRepaymentType()==1){
            //当借款期数为1月的利息算法
            if (tBorrow.getRepaymentMonth()==1){
                gInterest=(double)tBorrow.getBorrowAmount()*tBorrow.getYearRate()/1200;
            }else {
                //月利率
                Double yRate= (double)tBorrow.getYearRate()/1200;
                //月利息
                Double yLiXi=tBorrow.getBorrowAmount()*yRate/100;
                //借款实际总金额率
                //Math.pow(a,b)==求a的b次方
                Double zJinE=Math.pow((1+yRate),tBorrow.getRepaymentMonth());
                //借款实际总利率
                Double zLiLv=zJinE-1;
                //每月还款金额
                Double yHuanKuan=BigDecimalUtil.retainTo2(yLiXi*zJinE/zLiLv);
                //总还款金额
                Double zHuankan=BigDecimalUtil.retainTo2(yHuanKuan*tBorrow.getRepaymentMonth());
                //总利息
                gInterest=(zHuankan-tBorrow.getBorrowAmount()/100)*100;
            }
            //先息后本
        }else if (tBorrow.getRepaymentType()==2){
            //总利息
            gInterest=(double)tBorrow.getYearRate()*tBorrow.getBorrowAmount()*tBorrow.getRepaymentMonth()/1200;
        }
        gInterest=BigDecimalUtil.retainTo2(gInterest);
        tBorrow.setTotalInterest(gInterest);
        //---根据用户id求出用户的账户信息（可用余额，冻结金额等）
        QueryWrapper<TUserWallet> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("account_id",tBorrow.getBorrowUserId());
        tUserWallet=userWalletService.selectOne(queryWrapper);
        //用户的剩余授信额度=原授信额度-当前借款金额
        UpdateWrapper<TUserWallet> updateWrapper=new UpdateWrapper<>();
        updateWrapper.eq("account_id",tBorrow.getBorrowUserId()).set("residual_credit_line",tUserWallet.getResidualCreditLine()-tBorrow.getBorrowAmount());
        Integer newUserWallet=userWalletService.update(null,updateWrapper);


        System.out.println("+++++++++++++++"+tUserWallet);
        Map<String,Object> map=new HashMap<>();

        int num=borrowService.insert(tBorrow);
        if (num>0&&newUserWallet>0){
            map.put("code",200);
        }

        map.put("msg","借款失败!!!");
        return map;
    }

    /**
     * 后台借款数据查询
     * @return
     */
    @ResponseBody
    @RequestMapping("borrow/query")
    public Object BorrowQuery(QueryObject queryObject){
        System.out.println("获取数据:"+queryObject.getCurrentPage());
        Map<String,Object> map=new HashMap<>();
        QueryWrapper<TBorrow> queryWrapper=new QueryWrapper();
        Page<TBorrow> page=new Page<>(Long.parseLong(queryObject.getCurrentPage()),5);
        IPage<TBorrow> iPage=borrowService.selectPage(page,queryWrapper);
        System.out.println("总页数："+iPage.getPages());
        System.out.println("总记录数："+iPage.getTotal());
        iPage.getRecords().forEach(System.out::println);
        if (iPage!=null){
            System.err.println(iPage);
            map.put("code",200);
            String str=JSON.toJSONString(iPage);
            System.err.println(str);
            map.put("data",iPage);

        }
        return map;
    }

    /**
     * 审核
     * @param borrowId
     * @param borrowState
     * @return
     */
    @RequestMapping("borrow/audit")
    @ResponseBody
    public Object BorrowAudit(@RequestParam("borrowId")String borrowId,
                              @RequestParam("borrowState")long borrowState){

        System.err.println("进入审核");
        /*TBorrow tBorrow =new TBorrow();
        tBorrow.setId(borrowId);
        tBorrow.setBorrowState(borrowState);*/
        Map<String,Object> map=new HashMap<>();
        UpdateWrapper<TBorrow> updateWrapper=new UpdateWrapper<>();
        updateWrapper.eq("id",borrowId).set("borrow_state",borrowState);
        int num=borrowService.update(null,updateWrapper);
        if (num>0){
            map.put("code",200);
        }
        map.put("msg","修改失败!!!");
        return map;
    }

    /**
     * 加载借款信息
     * @param borrowId
     * @return
     */
    @RequestMapping("borrow/get/{borrowId}")
    @ResponseBody
    public Object BorrowGet(@PathVariable("borrowId") String borrowId){

        System.out.println("id:"+borrowId);
        QueryWrapper<TBorrow> queryWrapper=new QueryWrapper<>();
        Map<String,Object> map=new HashMap<>();
        queryWrapper.eq("id",borrowId);
        TBorrow tBorrow = borrowService.selectOne(queryWrapper);
        if (tBorrow!=null){

            map.put("data",tBorrow);
        }
        map.put("code",200);
        map.put("msg","加载失败!!!");
        map.put("data",tBorrow);
        return map;
    }

}
