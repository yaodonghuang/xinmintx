package com.xinmintx;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 启动程序
 * 
 * @author xinmintx
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class XinMinTxApplication
{
    public static void main(String[] args)
    {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(XinMinTxApplication.class, args);
    }
}