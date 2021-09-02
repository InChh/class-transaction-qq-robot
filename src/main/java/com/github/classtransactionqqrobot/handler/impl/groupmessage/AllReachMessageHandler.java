package com.github.classtransactionqqrobot.handler.impl.groupmessage;

import com.github.classtransactionqqrobot.entity.Dormitory;
import love.forte.simbot.api.message.events.MsgGet;
import love.forte.simbot.listener.ListenerContext;
import org.springframework.stereotype.Component;

/**
 * 处理人到齐的消息
 *
 * @author In_Chh
 * @since 1.0
 */
@Component("allReachMessageHandler")
public class AllReachMessageHandler extends AbstractGroupMessageHandler {

    @Override
    public boolean canHandle(String text) {
        return text.startsWith("齐");
    }

    @Override
    public String doHandle(MsgGet msg, ListenerContext listenerContext) {
        final String accountCode = msg.getAccountInfo().getAccountCode();
        final Dormitory dorm = dormitoryService.getDormByMasterCode(accountCode);
        //设置已归寝人数
        dorm.setCurrentPeople(dorm.getTotalPeople());
        //清空未归人员名单
        dorm.getPeopleNotReachList().clear();
        return "";
    }
}
