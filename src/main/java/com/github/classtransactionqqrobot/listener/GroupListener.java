package com.github.classtransactionqqrobot.listener;

import com.github.classtransactionqqrobot.handler.IMessageHandler;
import love.forte.simbot.annotation.Filter;
import love.forte.simbot.annotation.OnGroup;
import love.forte.simbot.api.message.events.GroupMsg;
import love.forte.simbot.api.sender.MsgSender;
import love.forte.simbot.filter.MatchType;
import love.forte.simbot.listener.ListenerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

/**
 * 消息监听器基类
 *
 * @author In_Chh
 */
@Component
public class GroupListener {
    Logger logger = LoggerFactory.getLogger(GroupListener.class);
    /**
     * 消息处理器列表
     */
    private List<IMessageHandler> messageHandlerList;

    private String dormGroupName;

    public GroupListener() {
    }

    @Autowired
    public GroupListener(List<IMessageHandler> messageHandlerList, String dormGroupName) {
        this.messageHandlerList = messageHandlerList;
        this.dormGroupName = dormGroupName;
    }

    /**
     * 监听并处理寝室群消息
     *
     * @param msg             群成员发送的消息容器
     * @param sender          送信器
     * @param listenerContext 上下文对象
     * @since 1.0
     */
    @OnGroup
    @Filter(value = "#", matchType = MatchType.STARTS_WITH, trim = true)
    public void dispacher(GroupMsg msg, MsgSender sender, ListenerContext listenerContext) {
        //获取去掉前缀#后的消息文本
        final String text = Objects.requireNonNull(msg.getText()).replace("#", "");
        //消息是否已经经过处理的标志
        boolean handleFlag = false;
        //回复的消息字符串
        String str = null;
        //判断是否为指定寝室群
        final String groupName = msg.getGroupInfo().getGroupName();
        if (!dormGroupName.equals(groupName)) {
            logger.warn("消息未在指定群发送");
            return;
        }

        if (logger.isDebugEnabled()) {
            logger.debug("开始遍历消息处理器");
        }
        try {
            for (IMessageHandler handler : messageHandlerList) {
                if (handler.canHandle(text)) {
                    str = handler.handle(msg, listenerContext);
                    handleFlag = true;
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            sender.SENDER.sendGroupMsg(msg, "出现异常：" + e.getMessage());
        }
        //如果未能处理消息
        if (!handleFlag) {
            if (logger.isDebugEnabled()) {
                logger.debug("未能找到能处理[{}]的处理器", text);
            }
            return;
        }
        //发送消息
        sender.SENDER.sendGroupMsg(msg, str);
    }
}
