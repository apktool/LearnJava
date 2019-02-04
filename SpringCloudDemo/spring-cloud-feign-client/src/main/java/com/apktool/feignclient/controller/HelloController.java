package com.apktool.feignclient.controller;

import com.apktool.feignclient.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    HelloService helloService;

    @RequestMapping(method = RequestMethod.GET)
    public String hi(@RequestParam(value = "name", defaultValue = "apktool") String name) {
        return helloService.hiService(name);
    }
}
