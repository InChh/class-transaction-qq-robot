package com.github.classtransactionqqrobot.handler.impl.groupmessage;

import com.github.classtransactionqqrobot.exception.PermissionDeniedException;
import com.github.classtransactionqqrobot.handler.IMessageHandler;
import com.github.classtransactionqqrobot.service.DormitoryService;
import com.github.classtransactionqqrobot.service.StudentService;
import love.forte.simbot.api.message.events.MsgGet;
import love.forte.simbot.listener.ListenerContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

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
    private AbstractGroupMessageHandlerPostProcesser groupMessagePostProcesser;

    @Override
    public String handle(MsgGet msg, ListenerContext listenerContext) throws PermissionDeniedException {
        String str;
        groupMessagePostProcesser.beforeHandle(msg, listenerContext);
        str = doHandle(msg, listenerContext);
        final String afterHandle = groupMessagePostProcesser.afterHandle(listenerContext);
        if (StringUtils.hasLength(afterHandle)) {
            str=afterHandle;
        }
        return str;
    }

    /**
     * 解析消息内容，并做相应处理
     *
     * @param msg             监听消息
     * @param listenerContext 上下文
     * @throws PermissionDeniedException 权限不足时抛出
     * @return 消息文本
     */
    public abstract String doHandle(MsgGet msg, ListenerContext listenerContext) throws PermissionDeniedException;
}
