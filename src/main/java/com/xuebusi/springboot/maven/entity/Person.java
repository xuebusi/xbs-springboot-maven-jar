package com.xuebusi.springboot.maven.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 测试
 * Created by xuebusi.com on 2017/8/16.
 */
public class Person implements Serializable{

    private Integer personId;
    private String personName;
    private Integer gender;
    private String personAddr;
    private Date birthday;

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getPersonAddr() {
        return personAddr;
    }

    public void setPersonAddr(String personAddr) {
        this.personAddr = personAddr;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Person{" +
                "personId=" + personId +
                ", personName='" + personName + '\'' +
                ", gender=" + gender +
                ", personAddr='" + personAddr + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
