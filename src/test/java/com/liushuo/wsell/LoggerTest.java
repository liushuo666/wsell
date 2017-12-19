package com.liushuo.wsell;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class LoggerTest {
    //private final Logger logger = LoggerFactory.getLogger(LoggerTest.class);
    @Test
    public void logTest() {
       /* logger.debug("debug............");
        logger.info("info..............");
        logger.warn("warn..............");
        logger.error("error...........");*/
        String name = "xiaoming";
        double height = 178.0;
        log.debug("debug:名字：" + name + "身高：" + height);
        log.info("info:名字：" + name + "身高：" + height);
        log.warn("warn:名字：" + name + "身高：" + height);
        log.error("error:名字：" + name + "身高：" + height);
        log.info("-------------------------可以不用拼接的方式-----------------------------");
        log.debug("debug:名字：{},身高：{}", name, height);
        log.info("info:名字：{},身高：{}", name, height);
        log.warn("warn:名字：{},身高：{}", name, height);
        log.error("error:名字：{},身高：{}", name, height);
        log.info(System.getProperty("user.dir"));
    }
}
