package com.github.classtransactionqqrobot.common.util;

import com.github.classtransactionqqrobot.entity.Dormitory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author In_Chh
 */
@Repository
@ConfigurationProperties(prefix = "transaction-bot.dormitories")
public class Dormitories {
    private List<Dormitory> dormitoryList;

    public Dormitories() {
    }

    public Dormitories(List<Dormitory> dormitoryList) {
        this.dormitoryList = dormitoryList;
    }

    public Dormitory getDormitoryByDormMasterCode(String dormMasterCode) {
        return dormitoryList.stream()
                .filter(d -> d.getDormMasterCode().equals(dormMasterCode))
                .collect(Collectors.toList())
                .get(0);
    }

    public List<Dormitory> getDormitoryList() {
        return dormitoryList;
    }

    public void setDormitoryList(List<Dormitory> dormitoryList) {
        this.dormitoryList = dormitoryList;
    }
}
