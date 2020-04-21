package com.sh.cloud.hello.service;

import org.springframework.stereotype.Service;

@Service
public class HelloService {

    public void server1(String num) {
        try {
            System.out.println(Thread.currentThread().getName() + "---start");
            Thread.sleep(5000);
            System.out.println(Thread.currentThread().getName() + "---end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
