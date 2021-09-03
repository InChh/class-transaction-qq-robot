package com.github.classtransactionqqrobot.handler.impl.groupmessage;

import com.github.classtransactionqqrobot.handler.MessageHandlerPostProcessor;
import com.github.classtransactionqqrobot.service.DormitoryService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 群消息处理器前置处理抽象类
 * @author In_Chh
 * @since 1.0.1
 */
public abstract class AbstractGroupMessageHandlerPostProcesser implements MessageHandlerPostProcessor {
    @Autowired
    protected DormitoryService dormitoryService;
}
