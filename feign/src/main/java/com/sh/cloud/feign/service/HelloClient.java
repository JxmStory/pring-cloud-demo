package com.sh.cloud.feign.service;

import feign.hystrix.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Auther: admin
 * @Date: 2019/1/9 10:19
 * @Description:
 */
@FeignClient(value = "service-hello", fallbackFactory = HelloClient.HelloClientHystrix.class)
public interface HelloClient {

    @RequestMapping(value = "/hello")
    public String hello(@RequestParam("name") String name) throws Throwable;

    @GetMapping(value = "/hi")
    public String hi(@RequestParam("name") String name);

    /**
     * 功能描述: HelloClient的内部类
     *          实现FallbackFactory接口 为HelloClient的方法提供断路调用
     * @auther: admin
     * @date: 2019/3/7 11:40
     */
    @Component
    class HelloClientHystrix implements FallbackFactory<HelloClient>{

        @Override
        public HelloClient create(Throwable throwable){
            return new HelloClient() {
                @Override
                public String hello(String name) throws Throwable{
                    if(throwable != null){
                        throw throwable;
                    }
                    return null;
                }

                @Override
                public String hi(String name){
                    return "Error ," + name;
                }
            };
        }
    }

}
