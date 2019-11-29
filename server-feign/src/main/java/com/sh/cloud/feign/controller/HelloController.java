package com.sh.cloud.feign.controller;

import com.sh.cloud.feign.service.HelloClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: admin
 * @Date: 2019/1/9 10:22
 * @Description:
 */
@RestController
public class HelloController {

    @Autowired
    private HelloClient helloClient;

    @RequestMapping("/hello")
    public String hello(@RequestParam("name") String name) throws Throwable{
        return helloClient.hello("feign-", name);
    }

    @GetMapping("/hi")
    public String hi(String name) throws Throwable {
        return helloClient.hi("feign-", name);
    }

    @GetMapping("/server1")
    public String server1(String num) throws Throwable {
        num = System.currentTimeMillis() + "!";
        System.out.println(num);
        return helloClient.server1(num);
    }
}
