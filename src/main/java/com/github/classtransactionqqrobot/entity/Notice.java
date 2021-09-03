package com.github.classtransactionqqrobot.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 通知实体类
 * @author In_Chh
 */
public class Notice {
    private String content;
    private Student sender;
    private Date createTime = new Date();
    private List<Student> receivedPeople = new ArrayList<>();

    public Notice() {
    }

    public Notice(String content, Student sender) {
        this.content = content;
        this.sender = sender;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Student getSender() {
        return sender;
    }

    public void setSender(Student sender) {
        this.sender = sender;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public List<Student> getReceivedPeople() {
        return receivedPeople;
    }

    public void setReceivedPeople(List<Student> receivedPeople) {
        this.receivedPeople = receivedPeople;
    }

    @Override
    public String toString() {
        return createTime
                + "\n来自" + sender.getName() + "的通知：\n"
                + content;
    }
}
