package com.apktool.feignclient.service;

import com.apktool.feignclient.service.impl.SchedualServiceHiHystric;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "service-hi", fallback = SchedualServiceHiHystric.class)
public interface HelloService {

    @RequestMapping(value = "/hi", method = RequestMethod.GET)
    public String hiService(@RequestParam(value = "name") String name);
}
