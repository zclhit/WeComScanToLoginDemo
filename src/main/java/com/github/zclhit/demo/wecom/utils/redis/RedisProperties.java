package com.github.zclhit.demo.wecom.utils.redis;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "spring.redis")
@Data
public class RedisProperties {

    private String host;

    private Integer port;

    private Integer database;

    private String password;

    private Integer timeout;

    private boolean testOnBorrow;

    private LettuceConfig lettuce;

    /**
     * special for Redisson config
     */
    private String address;

    @Data
    public static class LettuceConfig {
        private Integer maxActive;

        private Integer maxIdle;

        private Integer minIdle;

        private Integer maxWait;
    }
}
