package com.github.classtransactionqqrobot.common.util;

import com.github.classtransactionqqrobot.entity.Dormitory;

import java.util.Date;
import java.util.List;

/**
 * 处理机器人所回复消息的工具类
 *
 * @author In_Chh
 * @since 1.0
 */
public class MessageUtils {
    /**
     * 格式化宿舍信息
     *
     * @param list 宿舍对象列表
     * @return 格式化后的字符串，可直接发送
     */
    public static String format(List<Dormitory> list) {
        final StringBuilder stringBuilder = new StringBuilder()
                .append(new Date())
                .append("\n");
        list.forEach(e -> stringBuilder.append(e).append("\n"));
        return stringBuilder.toString();
    }
}
