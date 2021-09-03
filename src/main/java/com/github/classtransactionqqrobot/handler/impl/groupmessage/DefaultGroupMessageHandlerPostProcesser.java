package com.github.classtransactionqqrobot.handler.impl.groupmessage;

import com.github.classtransactionqqrobot.common.util.MessageUtils;
import com.github.classtransactionqqrobot.entity.Dormitory;
import love.forte.simbot.listener.ListenerContext;

import java.util.List;

/**
 * 默认消息处理器后置处理器类
 *
 * @author In_Chh
 */
public class DefaultGroupMessageHandlerPostProcesser extends AbstractGroupMessageHandlerPostProcesser {

    @Override
    public String afterHandle(ListenerContext listenerContext) {
        final List<Dormitory> dorms = dormitoryService.getDorms();
        return MessageUtils.format(dorms);
    }
}
