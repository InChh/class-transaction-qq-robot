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
    MONITOR_DEPUTY(3, "副班长"),
    /**
     * 学创委员
     */
    STUDY_COMMISSIONER(4,"学创委员"),
    /**
     * 体组委员
     */
    SPORT_COMMISSIONER(5, "体组委员"),
    /**
     * 文宣委员
     */
    PUBLICITY_COMMISSIONER(6, "文宣委员"),
    /**
     * 生心委员
     */
    GENERAL_AFFAIRS_COMMISSIONER(7, "生心委员");


    ClassRole(Integer value, String name) {
    }
}
