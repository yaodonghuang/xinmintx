package com.xinmintx.factory.api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@MapperScan("com.ximintx.factory.api.mapper")
@SpringBootApplication
@EnableTransactionManagement
@EnableScheduling
public class XinMinTxFactoryApi {
    public static void main(String[] args) {
        SpringApplication.run(XinMinTxFactoryApi.class,args);
    }
}
