package com.ljdo;

/**
 * 用户实体
 * @author fly
 * @create 2016-01-10 10:09
 */
public class User {

    private String userName;
    private String emailAddress;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}