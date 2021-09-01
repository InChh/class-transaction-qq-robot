package com.github.classtransactionqqrobot.service;

import com.github.classtransactionqqrobot.common.util.Dormitories;
import com.github.classtransactionqqrobot.entity.Dormitory;
import com.github.classtransactionqqrobot.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 归寝统计服务类
 *
 * @author In_Chh
 */
@Service
public class DormitoryService {

    private final Dormitories dormitories;

    @Autowired
    public DormitoryService(Dormitories dormitories) {
        this.dormitories = dormitories;
    }

    public List<Dormitory> getDorms() {
        return dormitories.getDormitoryList();
    }

    public Dormitory getDormByMasterCode(String masterCode) {
        assert masterCode != null;
        return dormitories.getDormitoryByDormMasterCode(masterCode);
    }

    public List<Dormitory> getDormsPeopleNotAllReach() {
        return getDorms().stream()
                .filter(e -> !e.getCurrentPeople().equals(e.getTotalPeople()))
                .collect(Collectors.toList());
    }

    public void setPeopleNotReachList(String masterCode, List<Student> peopleNotReachList) {
        final Dormitory dorm = getDormByMasterCode(masterCode);
        dorm.setPeopleNotReachList(peopleNotReachList);
    }

    public void resetAll() {
        dormitories.resetAll();
    }
}
