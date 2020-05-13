package com.xinmintx.merchant;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@MapperScan("com.xinmintx.merchant.mapper")
@SpringBootApplication
@EnableTransactionManagement
public class XinmintxMerchantApplication {

    public static void main(String[] args) {
        SpringApplication.run(XinmintxMerchantApplication.class, args);
    }

}
