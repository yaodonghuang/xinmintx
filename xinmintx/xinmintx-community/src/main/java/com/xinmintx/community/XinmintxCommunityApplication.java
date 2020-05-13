package com.xinmintx.community;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@MapperScan("com.xinmintx.community.mapper")
@SpringBootApplication
@EnableTransactionManagement
public class XinmintxCommunityApplication {

    public static void main(String[] args) {
        SpringApplication.run(XinmintxCommunityApplication.class, args);
    }

}
