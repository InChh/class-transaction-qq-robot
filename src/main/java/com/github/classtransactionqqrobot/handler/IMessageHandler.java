package com.github.classtransactionqqrobot.handler;

import com.github.classtransactionqqrobot.exception.PermissionDeniedException;
import love.forte.simbot.api.message.events.MsgGet;
import love.forte.simbot.listener.ListenerContext;

/**
 * 消息处理器接口
 *
 * @author In_Chh
 * @since 1.0
 */
public interface IMessageHandler {
    /**
     * 根据消息文本，判断是否能处理该消息
     *
     * @param text 消息正文
     * @return 能处理返回true，否则返回false
     * @since 1.0
     */
    boolean canHandle(String text);

    /**
     * 处理消息
     *
     * @param msg             监听消息
     * @param listenerContext 上下文
     * @throws PermissionDeniedException 权限不足时抛出
     * @since 1.0
     * @return 返回的消息字符串
     */
    String handle(MsgGet msg, ListenerContext listenerContext) throws PermissionDeniedException;
}
