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
        return true;
    }

    @Override
    protected String doHandle(MsgGet msg, ListenerContext listenerContext) throws PermissionDeniedException {
        PrivateMsg privateMsg = (PrivateMsg) msg;
        //TODO:将权限验证抽取出去
        final String accountCode = privateMsg.getAccountInfo().getAccountCode();
        final Student student = studentService.getStudentByCode(accountCode);
        if (student.getRole() == ClassRole.STUDENT) {
            throw new PermissionDeniedException();
        }


        final ScopeContext context = listenerContext.getContext(ListenerContext.Scope.GLOBAL);
        final boolean isEditing = (boolean) Optional.ofNullable(context.get("isEditing"))
                .orElse(false);
        if (!isEditing) {
            return "";
        }
        //获取消息内容
        final String content = privateMsg.getMsg();
        if (CommandConstant.SEND_NOTICE_COMMAND.equals(content)) {
            return "";
        }
        final List<Notice> notices = (List<Notice>) context.get("notices");
        if (notices != null) {
            notices.add(new Notice(content, student));
        }
        return "成功添加一条通知";
    }
}
