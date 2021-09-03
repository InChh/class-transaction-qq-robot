package com.github.classtransactionqqrobot.handler.impl.priivatemessage;

import com.github.classtransactionqqrobot.entity.Student;
import love.forte.simbot.api.message.events.MsgGet;
import love.forte.simbot.listener.ListenerContext;
import org.springframework.stereotype.Component;

/**
 * 默认私聊消息处理器前置处理类
 * @author In_Chh
 */
@Component
public class DefaultPrivateMessageHandlerPostProcessor extends AbstractPrivateMessageHandlerPostProcessor {
    @Override
    public void beforeHandle(MsgGet msg, ListenerContext listenerContext) {
        final String accountCode = msg.getAccountInfo().getAccountCode();
        final Student student = studentService.getStudentByCode(accountCode);
    }
}
