package com.github.classtransactionqqrobot.handler.impl.groupmessage;

import com.github.classtransactionqqrobot.entity.ClassRole;
import com.github.classtransactionqqrobot.entity.Student;
import com.github.classtransactionqqrobot.exception.PermissionDeniedException;
import love.forte.simbot.api.message.events.MsgGet;
import love.forte.simbot.listener.ListenerContext;
import org.springframework.stereotype.Component;

/**
 * 清空报寝信息消息处理器类
 * @author In_Chh
 */
@Component("clearMessageHandler")
public class ClearMessageHandler extends AbstractGroupMessageHandler{
    @Override
    public boolean canHandle(String text) {
        return text.startsWith("clear");
    }

    @Override
    public String doHandle(MsgGet msg, ListenerContext listenerContext) throws PermissionDeniedException {
        final Student student = studentService.getStudentByCode(msg.getAccountInfo().getAccountCode());
        if (student.getRole() != ClassRole.MONITOR && student.getRole() != ClassRole.MONITOR_DEPUTY) {
            throw new PermissionDeniedException();
        }
        dormitoryService.resetAll();
        return "";
    }
}
