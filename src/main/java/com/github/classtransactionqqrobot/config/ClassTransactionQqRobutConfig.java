package com.github.classtransactionqqrobot.config;

import com.github.classtransactionqqrobot.common.util.Dormitories;
import com.github.classtransactionqqrobot.common.util.Students;
import com.github.classtransactionqqrobot.handler.MessageHandlerPostProcessor;
import com.github.classtransactionqqrobot.handler.impl.DefaultGroupMessageHandlerPostProcesser;
import love.forte.common.ioc.annotation.Beans;
import love.forte.simbot.spring.autoconfigure.SimbotAppConfiguration;
import love.forte.simbot.spring.autoconfigure.SpringDependBeanFactory;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

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
