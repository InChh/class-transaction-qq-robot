package com.github.classtransactionqqrobot.handler.impl.priivatemessage;

import com.github.classtransactionqqrobot.entity.Notice;
import com.github.classtransactionqqrobot.entity.Student;
import com.github.classtransactionqqrobot.exception.PermissionDeniedException;
import love.forte.simbot.api.message.events.MsgGet;
import love.forte.simbot.bot.BotManager;
import love.forte.simbot.listener.ListenerContext;
import love.forte.simbot.listener.ScopeContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 学生回复的消息处理器类
 *
 * @author In_Chh
 * @since 1.0.2
 */
@Component
public class ReplyMessageHandler extends AbstractPrivateMessageHandler {
    private BotManager botManager;

    @Autowired
    public ReplyMessageHandler(BotManager botManager) {
        this.botManager = botManager;
    }

    @Override
    public boolean canHandle(String text) {
        //TODO:改善匹配机制，将text替换为MsgGet类型的参数
        return true;
    }

    @Override
    protected String doHandle(MsgGet msg, ListenerContext listenerContext) {
        //获取全局上下文对象
        final ScopeContext context = listenerContext.getContext(ListenerContext.Scope.GLOBAL);
        //获取要回复的字符串
        final String replyStr = (String) context.get("replyStr");
        //获取未回复学生名单
        final List<Student> notReplyStudentList = (List<Student>) context.get("notReplyStudentList");
        //如果不需要回复或者未回复学生名单为空,直接返回
        if (replyStr == null || notReplyStudentList == null || notReplyStudentList.size() == 0) {
            return "";
        }
        final String accountCode = msg.getAccountInfo().getAccountCode();
        final Student student = studentService.getStudentByCode(accountCode);
        notReplyStudentList.remove(student);
        //从上下文获取通知对象集合
        List<Notice> notices = (List<Notice>) Optional.ofNullable(context.get("notices"))
                .orElseGet(ArrayList::new);
        //从一条通知对象中获取发送者qq号
        final Student sender = notices.get(0).getSender();
        //向发送者发送消息
        final int totalCount = studentService.getStudentList().size();
        final int replyCount = totalCount - notReplyStudentList.size();
        botManager.getDefaultBot()
                .getSender()
                .SENDER.sendPrivateMsg(sender.getCode(), student.getName() + "已回复 " + replyCount + "/" + totalCount);
        return "";
    }
}
