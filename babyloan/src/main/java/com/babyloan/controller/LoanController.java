package com.babyloan.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.xml.ws.RequestWrapper;

@Controller
@RequestMapping("/loan")
public class LoanController {

    @RequestMapping("/borrow")
    public String ToBorrow(){
        System.err.println("我要借款————————");
        return "borrow";
    }
}
