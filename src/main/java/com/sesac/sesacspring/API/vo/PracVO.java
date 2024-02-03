package com.sesac.sesacspring.API.vo;

import lombok.Getter;
@Getter
public class PracVO {
    private String name;
    private String gender;
    private String year;
    private String month;
    private String day;
    private String hobby;

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }


}
