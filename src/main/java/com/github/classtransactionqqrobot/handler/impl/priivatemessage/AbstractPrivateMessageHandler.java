package com.github.classtransactionqqrobot.handler.impl.priivatemessage;

import com.github.classtransactionqqrobot.handler.IMessageHandler;
import com.github.classtransactionqqrobot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 私聊消息处理器抽象类
 *
 * @author In_Chh
 * @since 1.0
 */
public abstract class AbstractPrivateMessageHandler implements IMessageHandler {
    @Autowired
    protected StudentService studentService;
}
