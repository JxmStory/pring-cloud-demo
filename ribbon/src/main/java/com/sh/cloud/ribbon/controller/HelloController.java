package com.sh.cloud.ribbon.controller;

import com.sh.cloud.ribbon.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: admin
 * @Date: 2019/1/9 09:47
 * @Description:
 */
@RestController
public class HelloController {

    @Autowired
    private HelloService helloService;

    @RequestMapping("/hello")
    public String  hello(@RequestParam("name") String name){
        return helloService.hello("ribbon-" + name);
    }
}
