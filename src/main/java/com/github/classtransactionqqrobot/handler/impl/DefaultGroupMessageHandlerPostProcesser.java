package com.github.classtransactionqqrobot.handler.impl;

import com.github.classtransactionqqrobot.common.util.MessageUtils;
import com.github.classtransactionqqrobot.entity.Dormitory;
import com.github.classtransactionqqrobot.handler.MessageHandlerPostProcessor;
import com.github.classtransactionqqrobot.service.DormitoryService;
import love.forte.simbot.listener.ListenerContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 默认消息处理器后置处理器类
 *
 * @author In_Chh
 */
public class DefaultGroupMessageHandlerPostProcesser implements MessageHandlerPostProcessor {
    @Autowired
    private DormitoryService dormitoryService;

    @Override
    public String afterHandle(ListenerContext listenerContext) {
        final List<Dormitory> dorms = dormitoryService.getDorms();
        return MessageUtils.format(dorms);
    }
}
