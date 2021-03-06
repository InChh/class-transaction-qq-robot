package com.github.classtransactionqqrobot.common.util;

import com.github.classtransactionqqrobot.entity.Dormitory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 维护宿舍对象集合的工具类
 *
 * @author In_Chh
 * @since 1.0
 */
@Repository
@ConfigurationProperties(prefix = "class-transaction-qq-robot.dormitories")
public class Dormitories {
    private List<Dormitory> dormitoryList;

    public Dormitories() {
    }

    public Dormitories(List<Dormitory> dormitoryList) {
        this.dormitoryList = dormitoryList;
    }

    /**
     * 重置归寝信息
     */
    public void resetAll() {
        dormitoryList.forEach(Dormitory::reset);
    }

    /**
     * 通过宿舍长qq号获取对应宿舍对象
     *
     * @param dormMasterCode 宿舍长qq号
     * @return 宿舍对象，若未查到返回null
     */
    public Dormitory getDormitoryByDormMasterCode(String dormMasterCode) {
        for (Dormitory dormitory : dormitoryList) {
            if (dormitory.getDormMasterCode().equals(dormMasterCode)) {
                return dormitory;
            }
        }
        return null;
    }

    public List<Dormitory> getDormitoryList() {
        return dormitoryList;
    }

    public void setDormitoryList(List<Dormitory> dormitoryList) {
        this.dormitoryList = dormitoryList;
    }
}
