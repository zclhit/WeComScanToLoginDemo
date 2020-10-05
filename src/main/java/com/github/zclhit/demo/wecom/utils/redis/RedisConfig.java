package com.github.zclhit.demo.wecom.utils.redis;

import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;
import java.time.Duration;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Data
@Configuration
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ConditionalOnClass({RedisProperties.class})
@EnableCaching
public class RedisConfig {

  private final RedisProperties redisProperties;

  @Bean
  public RedisTemplate<Object, Object> redisTemplate(
      LettuceConnectionFactory lettuceConnectionFactory) {
    RedisTemplate<Object, Object> template = new RedisTemplate<>();

    GenericFastJsonRedisSerializer genericFastJsonRedisSerializer = new GenericFastJsonRedisSerializer();
    template.setValueSerializer(genericFastJsonRedisSerializer);
    template.setHashValueSerializer(genericFastJsonRedisSerializer);
    StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
    template.setKeySerializer(stringRedisSerializer);
    template.setHashKeySerializer(stringRedisSerializer);

    template.setConnectionFactory(lettuceConnectionFactory);
    return template;
  }

  @Bean
  @ConditionalOnMissingBean(StringRedisTemplate.class)
  public StringRedisTemplate stringRedisTemplate(
      LettuceConnectionFactory lettuceConnectionFactory) {
    StringRedisTemplate template = new StringRedisTemplate();
    template.setConnectionFactory(lettuceConnectionFactory);
    return template;
  }

  @Bean
  LettuceConnectionFactory lettuceConnectionFactory(
      GenericObjectPoolConfig genericObjectPoolConfig) {
    //stand-alone config
    RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
    redisStandaloneConfiguration.setDatabase(redisProperties.getDatabase());
    redisStandaloneConfiguration.setHostName(redisProperties.getHost());
    redisStandaloneConfiguration.setPort(redisProperties.getPort());
    redisStandaloneConfiguration.setPassword(RedisPassword.of(redisProperties.getPassword()));

    LettuceClientConfiguration clientConfig = LettucePoolingClientConfiguration.builder()
        .commandTimeout(Duration.ofMillis(redisProperties.getTimeout()))
        .poolConfig(genericObjectPoolConfig)
        .build();

    return new LettuceConnectionFactory(redisStandaloneConfiguration,
        clientConfig);
  }

  @Bean
  public GenericObjectPoolConfig genericObjectPoolConfig() {
    GenericObjectPoolConfig genericObjectPoolConfig = new GenericObjectPoolConfig();
    genericObjectPoolConfig.setMaxIdle(redisProperties.getLettuce().getMaxIdle());
    genericObjectPoolConfig.setMinIdle(redisProperties.getLettuce().getMinIdle());
    genericObjectPoolConfig.setMaxTotal(redisProperties.getLettuce().getMaxActive());
    genericObjectPoolConfig.setMaxWaitMillis(redisProperties.getLettuce().getMaxWait());
    return genericObjectPoolConfig;
  }

}
