package com.babyrepayment.controller;

import com.alibaba.fastjson.JSON;
import com.babyrepayment.service.Detail.DetailService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pojo.QueryObject;
import com.pojo.TRepayment;
import com.pojo.TRepaymentDetail;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@Controller
@RequestMapping("/finance/repayment")
public class DetailController {

    @Resource
    public DetailService detailService;

    /**
     * 收款明细查询
     * @return
     */
    @RequestMapping("/detail/query")
    @ResponseBody
    public Object DetailQuery(QueryObject queryObject){
        Map<String,Object> map=new HashMap<>();
        QueryWrapper<TRepaymentDetail> queryWrapper=new QueryWrapper();
        queryWrapper.eq("borrow_user_id",queryObject.getBorrowUserId());
        if (queryObject.getBeginDate()!=null&&queryObject.getEndDate()!=null){
            queryWrapper.between("deadline",queryObject.getBeginDate(),queryObject.getEndDate());
        }

        if (queryObject.getState()!=null&&Integer.parseInt(queryObject.getState()) > 0) {
            queryWrapper.eq("state",queryObject.getState());
        }

        Page<TRepaymentDetail> page=new Page<>(Long.parseLong(queryObject.getCurrentPage()),5);
        if (queryObject.getCurrentPage()!=null){
            page.setCurrent(Long.parseLong(queryObject.getCurrentPage()));
        }
        IPage<TRepaymentDetail> iPage=detailService.selectPage(page,queryWrapper);
        System.err.println("总页数："+iPage.getPages());
        System.err.println("总记录数："+iPage.getTotal());
        iPage.getRecords().forEach(System.out::println);
        if (iPage!=null){
            map.put("code",200);
            String str=JSON.toJSONString(iPage);
            System.err.println(str);
            map.put("data",iPage);
        }
        return map;
    }
}
