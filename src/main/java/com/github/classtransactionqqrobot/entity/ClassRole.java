package com.github.classtransactionqqrobot.entity;

/**
 * 班级职务枚举
 *
 * @author In_Chh
 */

public enum ClassRole {
    /**
     * 普通学生
     */
    STUDENT(0, "学生"),
    /**
     * 班长
     */
    MONITOR(1, "班长"),
    /**
     * 团支书
     */
    LEAGUE_BRANCH_SECRETARY(2, "团支书"),
    /**
     * 副班长
     */
    MONITOR_DEPUTY(3, "副班长");

    ClassRole(Integer value, String name) {
    }
}
