package com.github.zclhit.demo.wecom.client.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RetrieveUserIdResponse {
    private Integer errcode;
    private String errmsg;
    @JsonProperty("UserId")
    private String userId;
    @JsonProperty("OpenId")
    private String openId;
}
