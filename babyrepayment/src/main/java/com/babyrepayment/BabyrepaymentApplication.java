package com.babyrepayment;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.dao")
public class BabyrepaymentApplication {

    public static void main(String[] args) {
        SpringApplication.run(BabyrepaymentApplication.class, args);
    }

}
