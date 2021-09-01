package com.github.classtransactionqqrobot.common.util;

import com.github.classtransactionqqrobot.entity.Student;
import com.github.classtransactionqqrobot.exception.StudentNotFoundException;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author In_Chh
 */

@Repository
@ConfigurationProperties(prefix = "class-transaction-qq-robot.students")
public class Students {
    List<Student> studentList;

    public Students() {
    }

    public Students(List<Student> studentList) {
        this.studentList = studentList;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public Student getStudentByCode(String code) {
        for (Student student : studentList) {
            if (student.getCode().equals(code)) {
                return student;
            }
        }
        return null;
    }

    public List<Student> getStudentsByNames(List<String> names) {
        return names.stream()
                .filter(n -> this.getStudentByName(n) != null)
                .map(this::getStudentByName)
                .collect(Collectors.toList());
    }

    public Student getStudentByName(String name) {
        for (Student student : studentList) {
            if (student.getName().equals(name)) {
                return student;
            }
        }
        throw new StudentNotFoundException("未找到名称为：" + name + "的学生信息");
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }
}
