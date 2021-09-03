package com.github.classtransactionqqrobot.listener;

import com.github.classtransactionqqrobot.handler.IMessageHandler;
import love.forte.simbot.annotation.OnPrivate;
import love.forte.simbot.api.message.events.PrivateMsg;
import love.forte.simbot.api.sender.MsgSender;
import love.forte.simbot.listener.ListenerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 私聊消息监听器类
 *
 * @author In_Chh
 * @since 1.0.1
 */
@Component
public class PrivateListener {
    Logger logger = LoggerFactory.getLogger(PrivateListener.class);

    private final List<IMessageHandler> messageHandlerList;

    @Autowired
    public PrivateListener(List<IMessageHandler> messageHandlerList) {
        this.messageHandlerList = messageHandlerList;
    }

    @OnPrivate
    public void handleMessage(PrivateMsg msg, MsgSender sender, ListenerContext listenerContext) {
        final String text = msg.getText();
        if (logger.isDebugEnabled()) {
            logger.debug("监听到的消息内容为：{}", text);
        }
        //回复的消息内容
        String str = "";
        try {
            if (logger.isDebugEnabled()) {
                logger.debug("开始遍历消息处理器");
            }
            for (IMessageHandler handler : messageHandlerList) {
                if (handler.canHandle(text)) {
                    if (logger.isDebugEnabled()) {
                        logger.debug("{}开始处理", handler);
                    }
                    str = handler.handle(msg, listenerContext);
                }
            }
        } catch (Exception e) {
            logger.error("发生异常：");
            e.printStackTrace();
            str = e.getMessage();
        }
        //发送消息
        if (StringUtils.hasText(str)) {
            sender.SENDER.sendPrivateMsg(msg, str);
        }
    }
}
