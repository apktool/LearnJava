package com.hello.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloPythonController {

    /**
     * 浏览器访问http://localhost:8080/hellopython
     * 通过@RequestMapping 注解匹配映射请求的 URL，找到对应的方法。
     * 方法执行后返回 "hellopython"字符串 。通过 视图解析器 解析机制(prefix + returnVal + suffix)
     * 请求返回跳转页面 /WEB-INF/views/hellopython.jsp
     */

    @RequestMapping("/hellopython")
    public String helloPython() {
        return "hellopython";
    }
}
