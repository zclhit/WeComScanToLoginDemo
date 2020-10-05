package com.github.zclhit.demo.wecom.client;

import com.github.zclhit.demo.wecom.client.response.RetrieveTokenResponse;
import com.github.zclhit.demo.wecom.client.response.RetrieveUserIdResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@FeignClient(
    name = "WeChatTokenClient",
    url = "https://qyapi.weixin.qq.com/"
)
public interface WeChatWorkClient {

    @GetMapping("cgi-bin/gettoken")
    RetrieveTokenResponse getToken(
        @RequestParam("corpid") String corpid,
        @RequestParam("corpsecret") String corpsecret
    );

    @GetMapping("cgi-bin/user/getuserinfo")
    RetrieveUserIdResponse getUserByCode(
        @RequestParam("access_token") String accessToken,
        @RequestParam("code") String code
    );
}
