package com.xinmintx.community.configuration.redis;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: create by wcj
 * @date: 2019/6/27 0027
 * @time: 下午 14:59
 * @Description:
 */
@Configuration
@EnableConfigurationProperties(RedisProperties.class)
public class RedisAutoConfiguration {

    @Bean
    public Jedis createJedis(RedisProperties properties) {
        return new Jedis(properties.getHost(), properties.getPort());
    }

}
