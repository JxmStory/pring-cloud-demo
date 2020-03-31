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
 * @Description: 当server-hello-a 和 server-hello-b 都启动后 会实现负载均衡
 */
@FeignClient(value = "service-hello", fallbackFactory = HelloClient.HelloClientHystrix.class)
public interface HelloClient {

    @RequestMapping(value = "/hello")
    public String hello(@RequestParam("serverName") String serverName,
                        @RequestParam("userName") String userName) throws Throwable;

    @GetMapping(value = "/hi")
    public String hi(@RequestParam("serverName") String serverName,
                     @RequestParam("userName") String userName) throws Throwable;

    @GetMapping(value = "/server1")
    public String server1(@RequestParam("num") String num) throws Throwable;

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
                public String hello(String serverName, String userName) throws Throwable{
                    if(throwable != null){
                        throw throwable;
                    }
                    return null;
                }

                @Override
                public String hi(String serverName, String userName){
                    return "Error ," + userName;
                }

                @Override
                public String server1(String num) throws Throwable {
                    if(throwable != null){
                        throw throwable;
                    }
                    return null;
                }
            };
        }
    }

}
