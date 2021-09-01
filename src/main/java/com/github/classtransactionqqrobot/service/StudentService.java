package com.github.classtransactionqqrobot.service;

import com.github.classtransactionqqrobot.common.util.Students;
import com.github.classtransactionqqrobot.entity.Student;
import love.forte.common.ioc.annotation.Beans;
import love.forte.common.ioc.annotation.Depend;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 学生信息服务类
 *
 * @author In_Chh
 */
@Service
public class StudentService {
    private final Students students;

    @Depend
    public StudentService(Students students) {
        this.students = students;
    }

    /**
     * 通过qq号获取学生对象
     *
     * @param code qq号
     * @return 学生对象，若未找到则返回null
     */
    public Student getStudentByCode(String code) {
        return students.getStudentByCode(code);
    }

    public Student getStudentByName(String name) {
        return students.getStudentByName(name);
    }

    /**
     * 通过名称集合获取学生集合
     *
     * @param names 学生名称集合
     * @return 学生对象集合
     */
    public List<Student> getStudentsByNames(List<String> names) {
        return students.getStudentsByNames(names);
    }
}
