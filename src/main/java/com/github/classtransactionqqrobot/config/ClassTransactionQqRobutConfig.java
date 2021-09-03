package com.github.classtransactionqqrobot.config;

import com.github.classtransactionqqrobot.handler.impl.groupmessage.DefaultGroupMessageHandlerPostProcesser;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 机器人应用程序自动配置类
 *
 * @author In_Chh
 */
@Configuration
@ConfigurationProperties(prefix ="class-transaction-qq-robot")
public class ClassTransactionQqRobutConfig {

    @Bean
    public DefaultGroupMessageHandlerPostProcesser defaultGroupMessagePostProcesser() {
        return new DefaultGroupMessageHandlerPostProcesser();
    }

}
