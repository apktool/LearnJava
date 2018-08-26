package com.hello.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @RequestMapping("/helloworld")
    public String helloWorld() {
        System.out.println("---HelloWorld---");
        return "helloworld";
    }
}
