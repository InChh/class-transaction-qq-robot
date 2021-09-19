package com.github.classtransactionqqrobot.handler.impl.priivatemessage;

import com.github.classtransactionqqrobot.common.constant.CommandConstant;
import com.github.classtransactionqqrobot.entity.Student;
import com.github.classtransactionqqrobot.exception.PermissionDeniedException;
import love.forte.simbot.api.message.events.MsgGet;
import love.forte.simbot.api.sender.Sender;
import love.forte.simbot.bot.BotManager;
import love.forte.simbot.listener.ListenerContext;
import love.forte.simbot.listener.ScopeContext;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author In_Chh
 * @since 1.0.3
 */
@Component
public class SendTipMessageHandler extends AbstractPrivateMessageHandler {
    private final BotManager botManager;

    public SendTipMessageHandler(BotManager botManager) {
        this.botManager = botManager;
    }

    @Override
    public boolean canHandle(String text) {
        return CommandConstant.TIP_FLAG_COMMAND.equals(text);
    }

    @Override
    protected String doHandle(MsgGet msg, ListenerContext listenerContext) throws PermissionDeniedException {
        final ScopeContext context = listenerContext.getContext(ListenerContext.Scope.GLOBAL);
        List<Student> notReplyStudentList = (List<Student>) context.get("notReplyStudentList");
        if (notReplyStudentList != null) {
            final Sender sender = botManager.getDefaultBot().getSender().SENDER;
            notReplyStudentList.forEach(s -> sender.sendPrivateMsg(s.getCode(), "请及时回复"));
            sender.sendPrivateMsg(msg, "已提醒");
        }
        return "";
    }
}
