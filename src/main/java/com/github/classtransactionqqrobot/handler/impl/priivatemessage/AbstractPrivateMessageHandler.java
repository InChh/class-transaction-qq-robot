package com.github.classtransactionqqrobot.handler.impl.priivatemessage;

import com.github.classtransactionqqrobot.exception.PermissionDeniedException;
import com.github.classtransactionqqrobot.handler.IMessageHandler;
import com.github.classtransactionqqrobot.service.StudentService;
import love.forte.simbot.api.message.events.MsgGet;
import love.forte.simbot.listener.ListenerContext;
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

    @Override
    public String handle(MsgGet msg, ListenerContext listenerContext) throws PermissionDeniedException {
        return doHandle(msg,listenerContext);
    }

    protected abstract String doHandle(MsgGet msg, ListenerContext listenerContext) throws PermissionDeniedException;
}
