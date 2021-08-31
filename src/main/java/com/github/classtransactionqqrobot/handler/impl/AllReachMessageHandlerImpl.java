package com.github.classtransactionqqrobot.handler.impl;

import com.github.classtransactionqqrobot.handler.IMessageHandler;
import love.forte.simbot.api.message.events.MsgGet;
import love.forte.simbot.api.sender.MsgSender;
import love.forte.simbot.listener.ListenerContext;
import org.springframework.stereotype.Component;

/**
 * 处理
 * @author In_Chh
 */
@Component("allReachMessageHandlerImpl")
public class AllReachMessageHandlerImpl implements IMessageHandler {
    @Override
    public boolean canHandle(String text) {

    }

    @Override
    public void handle(MsgGet msg, MsgSender sender, ListenerContext listenerContext) {

    }
}
