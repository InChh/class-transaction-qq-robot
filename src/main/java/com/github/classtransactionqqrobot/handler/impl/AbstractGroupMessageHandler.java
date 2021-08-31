package com.github.classtransactionqqrobot.handler.impl;

import com.github.classtransactionqqrobot.handler.IMessageHandler;
import com.github.classtransactionqqrobot.service.DormitoryService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 群消息处理器抽象类，用于处理群消息
 *
 * @author In_Chh
 * @since 1.1
 */
public abstract class AbstractGroupMessageHandler implements IMessageHandler {
    protected DormitoryService dormitoryService;

    public AbstractGroupMessageHandler() {
    }

    @Autowired
    public AbstractGroupMessageHandler(DormitoryService dormitoryService) {
        this.dormitoryService = dormitoryService;
    }
}
