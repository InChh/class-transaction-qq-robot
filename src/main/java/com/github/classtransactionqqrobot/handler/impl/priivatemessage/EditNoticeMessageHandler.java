package com.github.classtransactionqqrobot.handler.impl.priivatemessage;

import com.github.classtransactionqqrobot.common.constant.CommandConstant;
import com.github.classtransactionqqrobot.entity.ClassRole;
import com.github.classtransactionqqrobot.entity.Notice;
import com.github.classtransactionqqrobot.entity.Student;
import com.github.classtransactionqqrobot.exception.PermissionDeniedException;
import love.forte.simbot.api.message.events.MsgGet;
import love.forte.simbot.api.message.events.PrivateMsg;
import love.forte.simbot.listener.ListenerContext;
import love.forte.simbot.listener.ScopeContext;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


/**
 * 编辑通知消息处理器类
 *
 * @author In_Chh
 * @since 1.0.1
 */
@Component
public class EditNoticeMessageHandler extends AbstractPrivateMessageHandler {


    @Override
    public boolean canHandle(String text) {
        //TODO:改善匹配机制，将text替换为MsgGet类型的参数
        return true;
    }

    @Override
    protected String doHandle(MsgGet msg, ListenerContext listenerContext) {
        PrivateMsg privateMsg = (PrivateMsg) msg;
        //获取上下文
        final ScopeContext context = listenerContext.getContext(ListenerContext.Scope.GLOBAL);
        //获取消息内容
        final String content = privateMsg.getMsg();
        //TODO:将权限验证抽取出去
        //如果是普通学生回复则不做处理
        final String accountCode = privateMsg.getAccountInfo().getAccountCode();
        final Student student = studentService.getStudentByCode(accountCode);
        if (student.getRole() == ClassRole.STUDENT) {
            return "";
        }
        //如果有其他人正在编辑，不作处理
        Student editor = (Student) context.get("editor");
        if (editor != null) {
            return "";
        }
        //获取编辑状态
        final boolean isEditing = (boolean) Optional.ofNullable(context.get("isEditing"))
                .orElse(false);
        if (!isEditing) {
            return "";
        }
        //如果是其他命令，不作处理
        if (CommandConstant.SEND_NOTICE_COMMAND.equals(content)) {
            return "";
        }
        //如果需要回复
        if (content.contains(CommandConstant.REPLY_FLAG)) {
            //获取需要回复的内容
            final int i = content.lastIndexOf(CommandConstant.REPLY_FLAG) + CommandConstant.REPLY_FLAG.length();
            final String replyStr = content.substring(i);
            context.set("replyStr", replyStr);
        }
        //获取通知列表
        final List<Notice> notices = (List<Notice>) context.get("notices");
        //向通知集合中添加通知对象
        if (notices != null) {
            notices.add(new Notice(content, student));
        }
        return "成功添加一条通知";
    }
}
