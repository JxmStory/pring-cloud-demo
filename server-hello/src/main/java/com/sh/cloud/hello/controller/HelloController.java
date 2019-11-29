package com.sh.cloud.hello.controller;

import com.sh.cloud.hello.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Value("${server.port}")
    String port;

    @Autowired
    private HelloService helloService;

    @RequestMapping("/hello")
    public String hello(@RequestParam(value = "serverName") String serverName,
                        @RequestParam(value = "userName") String userName) {
        return "hello, " + userName + " ,i am from port:" + port;
    }

    @RequestMapping(value = "/hi", method = RequestMethod.GET)
    public String hi(String serverName, String userName){
        return "hi, " + userName + "调用成功!";
    }

    @RequestMapping(value = "/server1", method = RequestMethod.GET)
    public String server1(String num){
        helloService.server1(num);
        return num;
    }
}
