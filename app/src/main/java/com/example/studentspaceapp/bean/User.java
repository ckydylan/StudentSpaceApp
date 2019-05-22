package com.example.studentspaceapp.bean;

import cn.bmob.v3.BmobUser;

public class User extends BmobUser {
   private String nickname;
   private Integer age;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
