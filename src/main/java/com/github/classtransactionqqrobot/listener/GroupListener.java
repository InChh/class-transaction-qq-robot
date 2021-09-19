package com.github.classtransactionqqrobot.listener;

import com.github.classtransactionqqrobot.common.util.TimeUtil;
import com.github.classtransactionqqrobot.handler.IMessageHandler;
import com.github.classtransactionqqrobot.handler.impl.groupmessage.AbstractGroupMessageHandler;
import love.forte.simbot.annotation.Filter;
import love.forte.simbot.annotation.OnGroup;
import love.forte.simbot.api.message.events.GroupMsg;
import love.forte.simbot.api.sender.MsgSender;
import love.forte.simbot.filter.MatchType;
import love.forte.simbot.listener.ListenerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    private final List<AbstractGroupMessageHandler> messageHandlerList;

    public GroupListener(List<AbstractGroupMessageHandler> messageHandlerList) {
        this.messageHandlerList = messageHandlerList;
    }

    @Value("${class-transaction-qq-robot.dorm-group-name}")
    private String dormGroupName;

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
        //时间检查
        if (!TimeUtil.timeCheck()) {
            sender.SENDER.sendGroupMsg(msg, "当前未在报寝时间段内");
            return;
        }
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
            for (AbstractGroupMessageHandler handler : messageHandlerList) {
                if (handler.canHandle(text)) {
                    str = handler.handle(msg, listenerContext);
                    handleFlag = true;
                    break;
                }
            }
            //如果未能处理消息
            if (!handleFlag) {
                if (logger.isDebugEnabled()) {
                    logger.debug("未能找到能处理[{}]的处理器", text);
                }
                str = "未知指令";
            }
        } catch (Exception e) {
            e.printStackTrace();
            str = e.getMessage();
        }
        //发送消息
        sender.SENDER.sendGroupMsg(msg, str);
    }

    public String getDormGroupName() {
        return dormGroupName;
    }

    public void setDormGroupName(String dormGroupName) {
        this.dormGroupName = dormGroupName;
    }
}
