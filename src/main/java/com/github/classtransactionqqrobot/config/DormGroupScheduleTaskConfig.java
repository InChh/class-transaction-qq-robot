package com.github.classtransactionqqrobot.config;

import com.github.classtransactionqqrobot.entity.Dormitory;
import com.github.classtransactionqqrobot.service.DormitoryService;
import love.forte.simbot.bot.Bot;
import love.forte.simbot.bot.BotManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.List;

/**
 * 报寝定时任务配置类
 *
 * @author In_Chh
 */
@Configuration
@ConfigurationProperties(prefix = "class-transaction-qq-robot.schedule")
@EnableScheduling
public class DormGroupScheduleTaskConfig implements SchedulingConfigurer {
    @Autowired
    DormitoryService dormitoryService;
    @Autowired
    BotManager botManager;

    @Value("${class-transaction-qq-robot.schedule.start-time-cron}")
    private String startTimeCron;

    @Value("${class-transaction-qq-robot.schedule.tip-time-cron}")
    private String tipTimeCron;

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.addCronTask(() -> dormitoryService.resetAll()
        , startTimeCron);
        taskRegistrar.addCronTask(this::tipTask, tipTimeCron);
    }

    private void tipTask() {
        final List<Dormitory> dorms = dormitoryService.getDormsPeopleNotAllReach();
        if (dorms.size() == 0) {
            return;
        }
        final Bot defaultBot = botManager.getDefaultBot();
        dorms.stream()
                .map(Dormitory::getDormMasterCode)
                .forEach(e -> defaultBot.getSender().SENDER.sendPrivateMsg(e, "报寝gkd gkd"));
    }
}
