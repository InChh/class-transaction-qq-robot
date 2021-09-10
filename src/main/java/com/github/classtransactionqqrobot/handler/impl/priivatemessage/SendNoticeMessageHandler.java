package com.github.classtransactionqqrobot.handler.impl.priivatemessage;

import com.github.classtransactionqqrobot.common.constant.CommandConstant;
import com.github.classtransactionqqrobot.entity.ClassRole;
import com.github.classtransactionqqrobot.entity.Notice;
import com.github.classtransactionqqrobot.entity.Student;
import com.github.classtransactionqqrobot.exception.PermissionDeniedException;
import love.forte.simbot.api.message.events.MsgGet;
import love.forte.simbot.api.sender.Sender;
import love.forte.simbot.bot.BotManager;
import love.forte.simbot.listener.ListenerContext;
import love.forte.simbot.listener.ScopeContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

/**
 * 发送通知消息处理器类
 *
 * @author In_Chh
 * @since 1.0.1
 */
@Component
public class SendNoticeMessageHandler extends AbstractPrivateMessageHandler {
    Logger logger = LoggerFactory.getLogger(SendNoticeMessageHandler.class);

    BotManager botManager;

    @Autowired
    public SendNoticeMessageHandler(BotManager botManager) {
        this.botManager = botManager;
    }

    @Override
    public boolean canHandle(String text) {
        return CommandConstant.SEND_NOTICE_COMMAND.equals(text);
    }

    @Override
    protected String doHandle(MsgGet msg, ListenerContext listenerContext) throws PermissionDeniedException {
        //TODO:将权限验证抽取出去
        final String accountCode = msg.getAccountInfo().getAccountCode();
        final Student student = studentService.getStudentByCode(accountCode);
        final List<Student> studentList = studentService.getStudentList();
        if (student.getRole() == ClassRole.STUDENT) {
            throw new PermissionDeniedException();
        }

        final ScopeContext context = listenerContext.getContext(ListenerContext.Scope.GLOBAL);
        //获取通知列表
        List<Notice> notices = (List<Notice>) context.get("notices");
        String replyStr = (String) context.get("replyStr");
        logger.debug("获取到通知：{}", notices);
        if (notices != null) {
            send(studentList, notices);
            //如果需要回复
            if (replyStr != null) {
                //拷贝学生名单
                List<Student> notReplyStudentList = new LinkedList<>(studentList);
                //除去发通知的班委
                notReplyStudentList.remove(student);
                //加入上下文中
                context.set("notReplyStudentList", notReplyStudentList);
            }
        }
        clear(context);
        return "";
    }

    /**
     * 发送通知
     *
     * @param studentList 学生集合
     * @param notices     通知集合
     */
    private void send(List<Student> studentList, List<Notice> notices) {
        final Sender sender = botManager.getDefaultBot().getSender().SENDER;
        //逐条给每个学生发送发送通知
        studentList.stream()
                .map(Student::getCode)
                .forEach(code -> notices.forEach(e -> sender.sendPrivateMsg(code, e.getContent())));
    }

    /**
     * 移除与编辑有关的相关状态
     *
     * @param context 上下文
     */
    private void clear(ScopeContext context) {
        context.remove("editor");
        context.remove("isEditing");
    }
}
