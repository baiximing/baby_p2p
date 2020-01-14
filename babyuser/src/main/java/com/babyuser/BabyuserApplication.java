package com.babyuser;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.dao")
public class BabyuserApplication {

    public static void main(String[] args) {

        SpringApplication.run(BabyuserApplication.class, args);
    }

}
