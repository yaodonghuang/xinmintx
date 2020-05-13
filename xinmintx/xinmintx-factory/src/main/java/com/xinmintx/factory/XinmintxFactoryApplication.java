package com.xinmintx.factory;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@MapperScan("com.xinmintx.factory.mapper")
@SpringBootApplication
@EnableTransactionManagement
@EnableScheduling
@EnableSwagger2
public class XinmintxFactoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(XinmintxFactoryApplication.class, args);
    }

}
