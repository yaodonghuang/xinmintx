package com.xinmintx.hstx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@MapperScan("com.xinmintx.hstx.mapper")
@SpringBootApplication
@EnableTransactionManagement
@EnableScheduling
@EnableAsync
public class XinmintxHstxApplication {

    public static void main(String[] args) {
        SpringApplication.run(XinmintxHstxApplication.class, args);
    }

}
