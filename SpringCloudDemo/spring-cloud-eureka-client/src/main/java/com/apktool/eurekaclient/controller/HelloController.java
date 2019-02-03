package com.apktool.eurekaclient.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Value("${server.port}")
    private String port;

    @RequestMapping(method = RequestMethod.GET)
    public String hi(@RequestParam(value = "name", defaultValue = "apktool") String name) {
        return "hi " + name + " ,i am from port:" + port;
    }
}
