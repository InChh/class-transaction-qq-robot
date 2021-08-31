package com.github.classtransactionqqrobot.handler.impl;

import com.github.classtransactionqqrobot.entity.Dormitory;
import com.github.classtransactionqqrobot.service.DormitoryService;
import love.forte.simbot.api.message.events.MsgGet;
import love.forte.simbot.api.sender.MsgSender;
import love.forte.simbot.listener.ListenerContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 处理人到齐的消息
 * @author In_Chh
 * @since 1.0
 */
@Component
public class AllReachMessageHandler extends AbstractGroupMessageHandler {
    public AllReachMessageHandler() {
        super();
    }

    @Autowired
    public AllReachMessageHandler(DormitoryService dormitoryService) {
        super(dormitoryService);
    }

    @Override
    public boolean canHandle(String text) {
        return text.contains("齐");
    }

    @Override
    public void handle(MsgGet msg, MsgSender sender, ListenerContext listenerContext) {
        final String accountCode = msg.getAccountInfo().getAccountCode();
        final Dormitory dorm = dormitoryService.getDormByMasterCode(accountCode);
        dorm.setCurrentPeople(dorm.getTotalPeople());
    }
}
