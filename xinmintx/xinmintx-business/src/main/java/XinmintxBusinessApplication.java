package com.xinmintx.business;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

@MapperScan("com.xinmintx.business.mapper")
@SpringBootApplication
@EnableTransactionManagement
public class XinmintxBusinessApplication {

    public static void main(String[] args) {
        SpringApplication.run(XinmintxBusinessApplication.class, args);
    }

}
