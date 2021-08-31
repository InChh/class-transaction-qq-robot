package com.github.classtransactionqqrobot.service;

import com.github.classtransactionqqrobot.entity.Dormitory;
import com.github.classtransactionqqrobot.common.util.Dormitories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
