package com.wen.crowd.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Admin {
    private Integer id;

    private String loginAcct;

    private String userPwd;

    private String userName;

    private String email;

    private String createTime;

    public Admin() {
    }

    public Admin(Integer id, String loginAcct, String userPwd, String userName, String email) {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        this.id = id;
        this.loginAcct = loginAcct;
        this.userPwd = userPwd;
        this.userName = userName;
        this.email = email;
        this.createTime = simpleDateFormat.format(date);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoginAcct() {
        return loginAcct;
    }

    public void setLoginAcct(String loginAcct) {
        this.loginAcct = loginAcct;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", loginAcct='" + loginAcct + '\'' +
                ", userPwd='" + userPwd + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}