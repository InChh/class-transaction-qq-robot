package com.github.classtransactionqqrobot.handler.impl.groupmessage;

import com.github.classtransactionqqrobot.exception.PermissionDeniedException;
import com.github.classtransactionqqrobot.handler.IMessageHandler;
import com.github.classtransactionqqrobot.handler.impl.DefaultGroupMessageHandlerPostProcesser;
import com.github.classtransactionqqrobot.service.DormitoryService;
import com.github.classtransactionqqrobot.service.StudentService;
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

    @Autowired
    protected StudentService studentService;

    @Autowired
    private DefaultGroupMessageHandlerPostProcesser groupMessagePostProcesser;

    @Override
    public String handle(MsgGet msg, ListenerContext listenerContext) throws PermissionDeniedException {
        groupMessagePostProcesser.beforeHandle(msg, listenerContext);
        doHandle(msg, listenerContext);
        return groupMessagePostProcesser.afterHandle(listenerContext);
    }

    /**
     * 解析消息内容，并做相应处理
     *
     * @param msg             监听消息
     * @param listenerContext 上下文
     * @throws PermissionDeniedException 权限不足时抛出
     */
    public abstract void doHandle(MsgGet msg, ListenerContext listenerContext) throws PermissionDeniedException;
}
