package com.github.classtransactionqqrobot.handler.impl.priivatemessage;

import com.github.classtransactionqqrobot.common.constant.CommandConstant;
import com.github.classtransactionqqrobot.entity.ClassRole;
import com.github.classtransactionqqrobot.entity.Notice;
import com.github.classtransactionqqrobot.entity.Student;
import com.github.classtransactionqqrobot.exception.PermissionDeniedException;
import love.forte.simbot.api.message.events.MsgGet;
import love.forte.simbot.listener.ListenerContext;
import love.forte.simbot.listener.ScopeContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * ”开始编辑“消息处理器类
 * @author In_Chh
 * @since 1.0.1
 */
@Component
public class StartEditingNoticeMessageHandler extends AbstractPrivateMessageHandler{
    Logger logger = LoggerFactory.getLogger(StartEditingNoticeMessageHandler.class);

    @Override
    public boolean canHandle(String text) {
        return CommandConstant.EDIT_NOTICE_COMMAND.equals(text);
    }

    @Override
    protected String doHandle(MsgGet msg, ListenerContext listenerContext) throws PermissionDeniedException {
        final String accountCode = msg.getAccountInfo().getAccountCode();
        if (logger.isDebugEnabled()) {
            logger.debug("开始处理{}发送的消息",accountCode);
        }
        //TODO:将权限验证抽取出去
        final Student student = studentService.getStudentByCode(accountCode);
        if (student.getRole() == ClassRole.STUDENT) {
            throw new PermissionDeniedException();
        }
        final ScopeContext context = listenerContext.getContext(ListenerContext.Scope.GLOBAL);
        context.set("isEditing",true);
        context.set("notices", new ArrayList<Notice>(3));
        return "已进入编辑模式";
    }
}
