package com.xinmintx.agent;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@MapperScan("com.xinmintx.agent.mapper")
@SpringBootApplication
@EnableTransactionManagement
@EnableScheduling
public class XinMinTxAgent {
    public static void main(String[] args) {
        SpringApplication.run(XinMinTxAgent.class, args);
    }

}
