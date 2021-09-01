package com.github.classtransactionqqrobot.common.util;

import com.github.classtransactionqqrobot.entity.Student;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author In_Chh
 */

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
        return studentList.stream()
                .filter(s -> s.getCode().equals(code))
                .collect(Collectors.toList())
                .get(0);
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }
}
