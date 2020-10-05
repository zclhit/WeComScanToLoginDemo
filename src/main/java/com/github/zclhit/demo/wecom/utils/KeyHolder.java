package com.github.zclhit.demo.wecom.utils;

import com.github.zclhit.demo.wecom.service.WeChatTokenService;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Getter
public class KeyHolder {
    private final String TOKEN_KEY = "access_token";

    private final WeChatTokenService weChatTokenService;
    private final RedisTemplate<Object, Object> redisTemplate;

    public String getAccessToken() {
        Object token = redisTemplate.opsForValue().get(TOKEN_KEY);
        if (Objects.isNull(token)) {
            return refreshToken();
        }
        return (String) token;
    }

    public String refreshToken() {
        String token = weChatTokenService.refreshLoginToken();
        redisTemplate.opsForValue().set(TOKEN_KEY, token, 2, TimeUnit.HOURS);
        return token;
    }
}
