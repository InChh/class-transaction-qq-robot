package com.github.classtransactionqqrobot.config;

import com.github.classtransactionqqrobot.common.util.Dormitories;
import com.github.classtransactionqqrobot.common.util.Students;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 配置相关数据
 *
 * @author In_Chh
 */
@ConfigurationProperties(prefix = "class-transaction-qq-robot")
public class ClassTransactionQqRobotProperties {
    private Dormitories dormitories;
    private Students students;
    private String dormGroupName;

    public Dormitories getDormitories() {
        return dormitories;
    }

    public void setDormitories(Dormitories dormitories) {
        this.dormitories = dormitories;
    }

    public Students getStudents() {
        return students;
    }

    public void setStudents(Students students) {
        this.students = students;
    }

    public String getDormGroupName() {
        return dormGroupName;
    }

    public void setDormGroupName(String dormGroupName) {
        this.dormGroupName = dormGroupName;
    }
}
