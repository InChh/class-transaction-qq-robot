package com.github.classtransactionqqrobot.listener;

import com.github.classtransactionqqrobot.common.constant.GroupConstant;
import com.github.classtransactionqqrobot.service.DormitoryService;
import love.forte.simbot.annotation.Filter;
import love.forte.simbot.annotation.OnGroup;
import love.forte.simbot.api.message.events.GroupMsg;
import love.forte.simbot.api.sender.Sender;
import love.forte.simbot.filter.MatchType;
import love.forte.simbot.listener.ListenerContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 消息监听器基类
 *
 * @author In_Chh
 */
@Component
@ConfigurationProperties(prefix = "transaction-bot.listener-action")
public class BaseListener {
    /**
     * 消息到方法名的映射信息
     */
    private Map<String, String> actionMap;

    protected DormitoryService dormitoryService;

    public BaseListener() {
    }

    @Autowired
    public BaseListener(DormitoryService dormitoryService) {
        this.dormitoryService = dormitoryService;
    }

    /**
     * 监听寝室群消息,根据不同的消息内容，调用相应的方法
     *
     * @param msg             群成员发送的消息容器
     * @param sender          送信器
     * @param listenerContext 上下文对象
     * @since 1.0
     */
    @OnGroup
    @Filter(value = "#", matchType = MatchType.STARTS_WITH, trim = true)
    public void dispacher(GroupMsg msg, Sender sender, ListenerContext listenerContext) {
        //获取去掉前缀#后的消息文本
        final String text = Objects.requireNonNull(msg.getText()).replace("#", "");
        //根据发送的消息，获取操作类型，调用相应方法
        final String action = getAction(text);
        //判断是否为指定寝室群
        final String groupName = msg.getGroupInfo().getGroupName();

        if (GroupConstant.DORM_GROUP_NAME.equals(groupName)) {
            final Method method;
            try {
                method = getClass().getDeclaredMethod(action, GroupMsg.class, Sender.class, ListenerContext.class);
                method.invoke(this, msg, sender, listenerContext);
            } catch (Exception e) {
                sender.sendGroupMsg(msg, "发生异常：" + e.getMessage());
            }
        }
    }

    /**
     * 根据消息文本获取方法名称
     *
     * @param text 消息文本
     * @return 对应方法名称
     * @since 1.0
     */
    private String getAction(String text) {
        return actionMap.entrySet().stream()
                .filter(a -> text.contains(a.getKey().substring(2)))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList())
                .get(0);
    }

    public Map<String, String> getActionMap() {
        return actionMap;
    }

    public void setActionMap(Map<String, String> actionMap) {
        this.actionMap = actionMap;
    }

    public DormitoryService getDormitoryService() {
        return dormitoryService;
    }

    public void setDormitoryService(DormitoryService dormitoryService) {
        this.dormitoryService = dormitoryService;
    }
}
