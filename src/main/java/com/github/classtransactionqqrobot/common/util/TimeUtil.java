package com.github.classtransactionqqrobot.common.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

/**
 * 时间相关工具类
 *
 * @author In_Chh
 */
@Component
@ConfigurationProperties(prefix = "class-transaction-qq-robot.time")
public class TimeUtil {
    private static String startTimeStr;
    private static String endTimeStr;

    /**
     * 检查是否处于报寝时间段内
     *
     * @return 在报寝时间段内为true，否则为false
     */
    public static boolean timeCheck() {
        final LocalTime now = LocalTime.now();
        final LocalTime start = LocalTime.parse(startTimeStr);
        final LocalTime end = LocalTime.parse(endTimeStr);
        return now.isAfter(start) && now.isBefore(end);
    }

    public String getStartTime() {
        return startTimeStr;
    }

    @Value("${class-transaction-qq-robot.time.start-time-str}")
    public void setStartTimeStr(String startTimeStr) {
        TimeUtil.startTimeStr = startTimeStr;
    }

    public String getEndTime() {
        return endTimeStr;
    }

    @Value("${class-transaction-qq-robot.time.end-time-str}")
    public void setEndTimeStr(String endTimeStr) {
        TimeUtil.endTimeStr = endTimeStr;
    }
}
