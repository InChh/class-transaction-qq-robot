package com.github.classtransactionqqrobot.common.util;

import com.github.classtransactionqqrobot.entity.Student;
import com.github.classtransactionqqrobot.exception.StudentNotFoundException;

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
        return null;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }
}
