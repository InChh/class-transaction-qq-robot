package com.github.classtransactionqqrobot.handler;

import love.forte.simbot.api.message.events.MsgGet;
import love.forte.simbot.listener.ListenerContext;

/**
 * 处理器调用处理方法前后执行，做预处理或收尾工作
 *
 * @author In_Chh
 */
public interface MessageHandlerPostProcessor {
    /**
     * 前置处理器
     *
     * @param msg             消息容器
     * @param listenerContext 上下文
     */
    default void beforeHandle(MsgGet msg, ListenerContext listenerContext) {
    }

    /**
     * 后置处理器
     *
     * @param listenerContext 上下文
     * @return 消息内容
     */
    default String afterHandle(ListenerContext listenerContext) {
        return "";
    }
}
