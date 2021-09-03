package com.github.classtransactionqqrobot.handler.impl.priivatemessage;

import com.github.classtransactionqqrobot.handler.MessageHandlerPostProcessor;
import com.github.classtransactionqqrobot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 抽象私聊消息处理器前置处理类
 * @author In_Chh
 * @since 1.0.1
 */
public abstract class AbstractPrivateMessageHandlerPostProcessor implements MessageHandlerPostProcessor {
    @Autowired
    StudentService studentService;
}
