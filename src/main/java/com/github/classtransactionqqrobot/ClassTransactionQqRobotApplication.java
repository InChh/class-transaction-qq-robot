package com.github.classtransactionqqrobot;

import com.github.classtransactionqqrobot.listener.BaseListener;
import love.forte.simbot.spring.autoconfigure.EnableSimbot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * 主启动类
 * @author In_Chh
 */
@EnableSimbot
@SpringBootApplication
public class ClassTransactionQqRobotApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClassTransactionQqRobotApplication.class, args);
    }

}
