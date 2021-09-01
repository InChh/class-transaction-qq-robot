package com.github.classtransactionqqrobot.entity;


import java.util.ArrayList;
import java.util.List;

/**
 * 宿舍实体类
 *
 * @author In_Chh
 */
public class Dormitory {
    /**
     * 楼栋号
     */
    private Integer buildingId = 0;
    /**
     * 房间号
     */
    private Integer roomId = 0;
    /**
     * 宿舍长qq号
     */
    private String dormMasterCode = "";
    /**
     * 宿舍总人数
     */
    private Integer totalPeople = 0;
    /**
     * 宿舍归寝人数
     */
    private Integer currentPeople = 0;
    /**
     * 未归寝人员名单
     */
    private List<Student> peopleNotReachList = new ArrayList<>();

    public Dormitory() {
    }

    public Dormitory(Integer buildingId, Integer roomId, String dormMasterCode, Integer totalPeople, Integer currentPeople, List<Student> peopleNotReachList) {
        this.buildingId = buildingId;
        this.roomId = roomId;
        this.dormMasterCode = dormMasterCode;
        this.totalPeople = totalPeople;
        this.currentPeople = currentPeople;
        this.peopleNotReachList = peopleNotReachList;
    }

    public Integer getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Integer buildingId) {
        this.buildingId = buildingId;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public String getDormMasterCode() {
        return dormMasterCode;
    }

    public void setDormMasterCode(String dormMasterCode) {
        this.dormMasterCode = dormMasterCode;
    }

    public Integer getTotalPeople() {
        return totalPeople;
    }

    public void setTotalPeople(Integer totalPeople) {
        this.totalPeople = totalPeople;
    }

    public Integer getCurrentPeople() {
        return currentPeople;
    }

    public void setCurrentPeople(Integer currentPeople) {
        if (currentPeople > totalPeople) {
            throw new IllegalArgumentException("已归寝人数不能大于总人数");
        }
        this.currentPeople = currentPeople;
    }

    public List<Student> getPeopleNotReachList() {
        return peopleNotReachList;
    }

    public void setPeopleNotReachList(List<Student> peopleNotReachList) {
        this.peopleNotReachList = peopleNotReachList;
        setCurrentPeople(this.totalPeople - peopleNotReachList.size());
    }

    @Override
    public String toString() {
        final StringBuilder s = new StringBuilder()
                .append(buildingId).append("栋")
                .append(roomId).append(" ")
                .append(currentPeople).append("/").append(totalPeople);
        if (peopleNotReachList.size() != 0) {
            s.append("，").append("未归").append(peopleNotReachList);
        }
        return s.toString();
    }
}
