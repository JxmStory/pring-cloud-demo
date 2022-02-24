package com.sh.cloud.feign;

import com.sh.cloud.feign.common.CommonClient;
import com.sh.cloud.feign.common.FeignClientUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FeignApplication.class)
public class FeignClientTest {

    private final static Logger logger = LoggerFactory.getLogger(FeignClientTest.class);

    @Test
    public void hello() throws Throwable {
        String[] severNames = {"service-hello-a", "service-hello-b"};
        String path = "";
        for (String severName : severNames) {
            CommonClient commonClient = FeignClientUtils.build(
                    severName,
                    path,
                    CommonClient.class,
                    CommonClient.CommonClientHystrix.class);
            String result1 = commonClient.hello("a", "b");
            logger.info("调用 {} 服务hello方法 返回:{}", severName, result1);
            String result2 = commonClient.hi("c", "d");
            logger.info("调用 {} 服务hi方法 返回:{}", severName, result2);
        }
    }
}
