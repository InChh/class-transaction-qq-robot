package com.github.classtransactionqqrobot.service;

import com.github.classtransactionqqrobot.entity.Dormitory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class DormitoryServiceTest {
    @Autowired
    private DormitoryService dormitoryService;

    @Test
    void getDorms() {
        final List<Dormitory> dorms = dormitoryService.getDorms();
        System.out.println(dorms);
    }

    @Test
    void getDormByMasterCode() {
    }
}