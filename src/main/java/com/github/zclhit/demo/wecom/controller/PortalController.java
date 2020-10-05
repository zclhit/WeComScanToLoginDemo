package com.github.zclhit.demo.wecom.controller;

import com.github.zclhit.demo.wecom.service.WeChatRedirectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
@RequiredArgsConstructor
@Slf4j
public class PortalController {
    private final WeChatRedirectService weChatRedirectService;

    @GetMapping
    public String getIndex(Model model) {
        return "index";
    }

    @GetMapping("/code")
    public String handleWeChatRedirect(Model model, @RequestParam("code") String code,
        @RequestParam("appid") String appid) {
        String userId = weChatRedirectService.getUserIdByCode(code, appid);
        log.info("get user login success, user id ={}", userId);

        model.addAttribute("userId", userId);
        return "userPage";
    }
}
