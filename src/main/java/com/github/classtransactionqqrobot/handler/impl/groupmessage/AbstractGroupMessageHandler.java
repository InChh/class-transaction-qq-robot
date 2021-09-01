package com.github.classtransactionqqrobot.handler.impl.groupmessage;

import com.github.classtransactionqqrobot.handler.IMessageHandler;
import com.github.classtransactionqqrobot.handler.impl.DefaultGroupMessageHandlerPostProcesser;
import com.github.classtransactionqqrobot.service.DormitoryService;
import love.forte.simbot.api.message.events.MsgGet;
import love.forte.simbot.listener.ListenerContext;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 群消息处理器抽象类，用于处理群消息
 *
 * @author In_Chh
 * @since 1.1
 */
public abstract class AbstractGroupMessageHandler implements IMessageHandler {
    @Autowired
    protected DormitoryService dormitoryService;

    private DefaultGroupMessageHandlerPostProcesser groupMessagePostProcesser;

    @Override
    public String handle(MsgGet msg, ListenerContext listenerContext) {
        groupMessagePostProcesser.beforeHandle(msg, listenerContext);
        doHandle(msg, listenerContext);
        return groupMessagePostProcesser.afterHandle(listenerContext);
    }

    /**
     * 解析消息内容，并做相应处理
     *
     * @param msg             消息容器
     * @param listenerContext 上下文
     */
    public abstract void doHandle(MsgGet msg, ListenerContext listenerContext);
}
