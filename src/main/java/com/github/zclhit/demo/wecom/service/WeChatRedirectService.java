package com.github.zclhit.demo.wecom.service;

import com.github.zclhit.demo.wecom.client.WeChatWorkClient;
import com.github.zclhit.demo.wecom.utils.KeyHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WeChatRedirectService {
    private final KeyHolder keyHolder;
    private final WeChatWorkClient weChatCodeClient;

    public String getUserIdByCode(String code, String appid) {
        String accessToken = keyHolder.getAccessToken();

        //TODO: need to process with accessToken invalid condition
        return weChatCodeClient.getUserByCode(accessToken, code).getUserId();
    }
}
