package com.github.classtransactionqqrobot.handler.impl.groupmessage;

import com.github.classtransactionqqrobot.entity.Student;
import love.forte.simbot.api.message.events.MsgGet;
import love.forte.simbot.listener.ListenerContext;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * 人未到齐消息处理器类，用于统计未归寝人员名单
 *
 * @author In_Chh
 * @since 1.0
 */
@Component("notAllReachMessageHandler")
public class NotAllReachMessageHandler extends AbstractGroupMessageHandler {
    @Override
    public boolean canHandle(String text) {
        return text.contains("未归");
    }

    @Override
    public String doHandle(MsgGet msg, ListenerContext listenerContext) {
        //获取消息文本
        final String text = msg.getText();
        //获取宿舍长qq号
        final String masterCode = msg.getAccountInfo().getAccountCode();
        assert text != null;
        //去除无用信息
        final String t = text.replaceAll("[#未归]", "");
        //拆分字符串，形成未归人员名字集合
        final String[] names = t.split("，");
        //通过名字集合获取Student对象集合
        final List<Student> peopleNotReach = studentService.getStudentsByNames(Arrays.asList(names));
        //设置宿舍长对应宿舍对象的未归人员名单
        dormitoryService.setPeopleNotReachList(masterCode, peopleNotReach);
        return "";
    }
}
