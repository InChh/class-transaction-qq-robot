package com.github.classtransactionqqrobot.listener;

import com.github.classtransactionqqrobot.common.constant.CommandConstant;
import com.github.classtransactionqqrobot.entity.ClassRole;
import com.github.classtransactionqqrobot.entity.Notice;
import com.github.classtransactionqqrobot.entity.Student;
import com.github.classtransactionqqrobot.service.StudentService;
import love.forte.simbot.annotation.Filter;
import love.forte.simbot.annotation.OnPrivate;
import love.forte.simbot.api.message.events.PrivateMsg;
import love.forte.simbot.api.sender.MsgSender;
import love.forte.simbot.filter.MatchType;
import love.forte.simbot.listener.ListenerContext;
import love.forte.simbot.listener.ScopeContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("unchecked")
@Component
public class NoticeListener {
    private final StudentService studentService;
    Logger logger = LoggerFactory.getLogger(NoticeListener.class);

    public NoticeListener(StudentService studentService) {
        this.studentService = studentService;
    }

    @OnPrivate
    @Filter(value = CommandConstant.EDIT_NOTICE_COMMAND, trim = true, matchType = MatchType.CONTAINS)
    public void startEditNotice(PrivateMsg msg, MsgSender msgSender, ListenerContext listenerContext) {
        if (permissionCheck(getStudent(msg))) {
            return;
        }
        final ScopeContext context = listenerContext.getContext(ListenerContext.Scope.GLOBAL);
        if (editCheck(context)) {
            msgSender.SENDER.sendPrivateMsg(msg, "其他人正在编辑通知，请稍候");
        } else {
            final List<Notice> notices = new ArrayList<>();
            context.set("isEditing", true);
            context.set("notices", notices);
            msgSender.SENDER.sendPrivateMsg(msg, "已进入编辑模式");
        }
    }

    @OnPrivate
    public void editNotice(PrivateMsg msg, MsgSender msgSender, ListenerContext listenerContext) {
        ScopeContext context = listenerContext.getContext(ListenerContext.Scope.GLOBAL);
        if (!editCheck(context)) {
            msgSender.SENDER.sendPrivateMsg(msg, "当前未处于编辑通知状态");
            return;
        }
        String content = msg.getMsg();
        final List<Notice> notices = (List<Notice>) context.get("notices");
        Student student = getStudent(msg);
        //如果需要回复，获取回复字符串并添加到上下文
        replyCheck(context, content);
        if (notices != null) {
            notices.add(new Notice(content, student));
            msgSender.SENDER.sendPrivateMsg(msg, "成功添加一条通知");
        }
    }

    @OnPrivate
    @Filter(value = CommandConstant.SEND_NOTICE_COMMAND, trim = true, matchType = MatchType.CONTAINS)
    public void sendNotice(PrivateMsg msg, MsgSender msgSender, ListenerContext listenerContext) {

    }

    @OnPrivate
    @Filter(value = CommandConstant.CLEAR_NOTICE_COMMAND)
    public void clearNotice(PrivateMsg msg, MsgSender msgSender, ListenerContext listenerContext) {

    }

    @OnPrivate
    @Filter(value = CommandConstant.SEND_TIP_COMMAND, trim = true, matchType = MatchType.CONTAINS)
    public void sendTip(PrivateMsg msg, MsgSender msgSender, ListenerContext listenerContext) {

    }

    /**
     * 检查发信人是否为班委
     *
     * @param student 发信人对象
     * @return boolean
     */
    private boolean permissionCheck(Student student) {
        return student.getRole() != ClassRole.STUDENT;
    }

    /**
     * 检查是否处于编辑通知状态
     *
     * @param context 上下文
     * @return boolean
     */
    private boolean editCheck(ScopeContext context) {
        //获取编辑状态
        return (boolean) Optional.ofNullable(context.get("isEditing"))
                .orElse(false);
    }

    private void replyCheck(ScopeContext context, String content) {
        if (content.contains(CommandConstant.REPLY_FLAG)) {
            final int i = content.lastIndexOf(CommandConstant.REPLY_FLAG) + CommandConstant.REPLY_FLAG.length();
            final String replyStr = content.substring(i);
            context.set("replyStr", replyStr);
        }
    }

    private Student getStudent(PrivateMsg msg) {
        String accountCode = msg.getAccountInfo().getAccountCode();
        return studentService.getStudentByCode(accountCode);
    }
}
