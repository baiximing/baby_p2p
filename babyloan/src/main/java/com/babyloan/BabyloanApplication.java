package com.babyloan;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.dao")
public class BabyloanApplication {

    public static void main(String[] args) {
        SpringApplication.run(BabyloanApplication.class, args);
    }

}
