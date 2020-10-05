package com.github.zclhit.demo.wecom.service;

import com.github.zclhit.demo.wecom.client.WeChatWorkClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class WeChatTokenService {

    private final WeChatWorkClient weChatWorkClient;

    public String refreshLoginToken() {
        return weChatWorkClient
            .getToken("wwfdef7cb83450039d", "r6CzFefNoOjFDSetVmqYnGC32GXnwYV-kl9j5UnIR-M")
            .getAccessToken();
    }
}
