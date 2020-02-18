package com.babyuser.controller;

import com.alibaba.fastjson.JSON;
import com.babyuser.service.system.SysemService;
import com.babyuser.service.system.SystemLogService;
import com.babyuser.service.system.SystemServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pojo.TLoginLog;
import com.pojo.TSystemDictionaryItem;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/system")
public class SystemController {

    @Resource
    private SysemService sysemService;

    @Resource
    private SystemLogService systemLogService;

    /**
     * 获取学历，收入，婚姻情况，住房情况的数据
     * @return
     */
    @RequestMapping("/dictionaryItem/getAll")
    @ResponseBody
    public Object GetAll(){
        System.out.println("获取下拉框的数据");
        Map<String,Object> map=new HashMap<>();
        List<TSystemDictionaryItem> list=new ArrayList<>();
        QueryWrapper<TSystemDictionaryItem> queryWrapper=new QueryWrapper<>();
        list=sysemService.selectList(queryWrapper);
        System.out.println("list:"+list);
        if (list!=null){
            map.put("code",200);
            map.put("data",list);
        }

        map.put("msg","加载下拉框数据失败！！！");
        return map;
    }

    @RequestMapping("/loginlog/query")
    @ResponseBody
    public Object LoginLogQuery(){
        System.out.println("获取登陆日志");
        Map<String,Object> map=new HashMap<>();
        QueryWrapper<TLoginLog> queryWrapper=new QueryWrapper();
        Page<TLoginLog> page=new Page<>(1,10);
        IPage<TLoginLog> iPage=systemLogService.selectPage(page,queryWrapper);
        System.out.println("总页数："+iPage.getPages());
        System.out.println("总记录数："+iPage.getTotal());
        iPage.getRecords().forEach(System.out::println);
        /*String str=JSON.toJSONString(iPage.getRecords());
        System.err.println(str);*/
        if (iPage!=null){
            System.err.println(iPage);
            map.put("code",200);
            map.put("data",iPage.getRecords());
        }
        return map;
    }
}
