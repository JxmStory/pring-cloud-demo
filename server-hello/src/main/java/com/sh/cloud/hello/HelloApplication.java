package com.sh.cloud.hello;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: admin
 * @Date: 2019/1/9 09:13
 * @Description:
 */
@SpringBootApplication
@EnableEurekaClient
@RestController
public class HelloApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelloApplication.class, args);
    }

    @Value("${server.port}")
    String port;

    @RequestMapping("/hello")
    public String hello(@RequestParam(value = "name") String name) {
        return "hello, " + name + " ,i am from port:" + port;
    }

    @RequestMapping(value = "/hi", method = RequestMethod.GET)
    public String hi(String name){
        return "hi, " + name + "调用成功!";
    }

}

