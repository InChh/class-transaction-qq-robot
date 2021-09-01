package com.github.classtransactionqqrobot.config;

import com.github.classtransactionqqrobot.common.util.Dormitories;
import com.github.classtransactionqqrobot.common.util.Students;
import com.github.classtransactionqqrobot.handler.MessageHandlerPostProcessor;
import com.github.classtransactionqqrobot.handler.impl.DefaultGroupMessageHandlerPostProcesser;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 机器人应用程序自动配置类
 *
 * @author In_Chh
 */
@Configuration
@EnableConfigurationProperties(ClassTransactionQqRobotProperties.class)
public class ClassTransactionQqRobutAutoConfiguration {
    @Bean
    public Dormitories dormitories(ClassTransactionQqRobotProperties properties) {
        return properties.getDormitories();
    }

    @Bean
    public Students students(ClassTransactionQqRobotProperties properties) {
        return properties.getStudents();
    }

    @Bean
    public String dormGroupName(ClassTransactionQqRobotProperties properties) {
        return properties.getDormGroupName();
    }

    @Bean
    @ConditionalOnMissingBean(MessageHandlerPostProcessor.class)
    public DefaultGroupMessageHandlerPostProcesser defaultGroupMessagePostProcesser() {
        return new DefaultGroupMessageHandlerPostProcesser();
    }
}
