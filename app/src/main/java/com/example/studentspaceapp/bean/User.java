package com.example.studentspaceapp.bean;

import cn.bmob.v3.BmobObject;

public class User extends BmobObject {
    private String userName;
    private String password;

    public User(){
        this.setTableName("Student");
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String usercount) {
        this.userName = usercount;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
