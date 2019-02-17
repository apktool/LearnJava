package com.apktool.feignclient.service.impl;

import com.apktool.feignclient.service.HelloService;
import org.springframework.stereotype.Component;

@Component
public class SchedualServiceHiHystric implements HelloService {
    @Override
    public String hiService(String name) {
        return "sorry " + name;
    }
}
