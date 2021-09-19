package com.github.classtransactionqqrobot.handler.impl.priivatemessage;

import com.github.classtransactionqqrobot.common.constant.CommandConstant;
import com.github.classtransactionqqrobot.entity.ClassRole;
import com.github.classtransactionqqrobot.entity.Notice;
import com.github.classtransactionqqrobot.entity.Student;
import com.github.classtransactionqqrobot.exception.PermissionDeniedException;
import love.forte.simbot.api.message.events.MsgGet;
import love.forte.simbot.bot.BotManager;
import love.forte.simbot.listener.ListenerContext;
import love.forte.simbot.listener.ScopeContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * ”开始编辑“消息处理器类
 *
 * @author In_Chh
 * @since 1.0.1
 */
@Component
public class StartEditingNoticeMessageHandler extends AbstractPrivateMessageHandler {
    private Logger logger = LoggerFactory.getLogger(StartEditingNoticeMessageHandler.class);

    private final BotManager botManager;

    public StartEditingNoticeMessageHandler(BotManager botManager) {
        this.botManager = botManager;
    }

    @Override
    public boolean canHandle(String text) {
        return CommandConstant.EDIT_NOTICE_COMMAND.equals(text);
    }

    @Override
    protected String doHandle(MsgGet msg, ListenerContext listenerContext) throws PermissionDeniedException {
        //获取上下文
        final ScopeContext context = listenerContext.getContext(ListenerContext.Scope.GLOBAL);
        //获取消息发送者qq号
        final String accountCode = msg.getAccountInfo().getAccountCode();
        //TODO:将权限验证抽取出去
        final Student student = studentService.getStudentByCode(accountCode);
        if (student.getRole() == ClassRole.STUDENT) {
            throw new PermissionDeniedException();
        }
        //如果有其他人正在编辑，不作处理
        Student editor = (Student) context.get("editor");
        if (editor != null) {
            return "其他人正在编辑，请稍等";
        }
        context.set("isEditing", true);
        context.set("editor", student);
        List<Notice> notices = (List<Notice>) context.get("notices");
        if (notices == null) {
            notices = new ArrayList<>(3);
            context.set("notices", notices);
        }
        notices.clear();
        botManager.getDefaultBot().getSender().SENDER.sendPrivateMsg(msg, "已进入编辑模式");
        return "";
    }
}
