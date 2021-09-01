package com.github.classtransactionqqrobot.entity;

/**
 * 班级学生实体类
 *
 * @author In_Chh
 */
public class Student {
    /**
     * 学号
     */
    private String id;
    /**
     * 学生姓名
     */
    private String name;
    /**
     * 学生qq号
     */
    private String code;
    /**
     * 学生职务
     */
    private ClassRole role;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ClassRole getRole() {
        return role;
    }

    public void setRole(ClassRole role) {
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
