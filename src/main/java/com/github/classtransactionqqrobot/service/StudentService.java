package com.github.classtransactionqqrobot.service;

import com.github.classtransactionqqrobot.common.util.Students;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    private final Students students;

    @Autowired
    public StudentService(Students students) {
        this.students = students;
    }
}
