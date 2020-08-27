package com.apktool.jetty.spring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author apktool
 * @package com.apktool.jetty.jersey.controller
 * @class HelloController
 * @description TODO
 * @date 2020-08-28 01:00
 */

@RestController
@RequestMapping("hello")
public class HelloController {

    @GetMapping("ping")
    public String getMsg() {
        String output = "Spring say : " + "pong";
        return output;
    }

}
