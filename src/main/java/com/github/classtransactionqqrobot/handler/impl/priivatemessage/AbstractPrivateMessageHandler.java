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

    @Autowired
    private PrivateMessageHandlerPostProcessor postProcesser;

    @Override
    public String handle(MsgGet msg, ListenerContext listenerContext) throws PermissionDeniedException {
        return doHandle(msg,listenerContext);
    }

    /**
     * 处理监听到的私聊消息
     * @param msg 监听消息
     * @param listenerContext 上下文
     * @return 回复的消息内容
     * @throws PermissionDeniedException 操作所需权限不足时抛出
     */
    protected abstract String doHandle(MsgGet msg, ListenerContext listenerContext) throws PermissionDeniedException;
}
