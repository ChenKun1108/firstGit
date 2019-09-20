package com.pinyougou.user.controller;

//Created by  2019/9/21

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("login")
public class LoginController {

    @RequestMapping("name")
    public Map showName() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        Map hashMap = new HashMap<>();
        hashMap.put("loginName",name);
        return hashMap;
    }
}
