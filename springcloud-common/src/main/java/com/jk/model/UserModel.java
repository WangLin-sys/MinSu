package com.jk.model;

public class UserModel {

    private Integer userId;
    private String  userNiCheng;    // 昵称
    private String  userAccount;    // 账号
    private String  userPasw;       // 密码
    private String  userPhone;      // 手机号
    private String  userMail;       // 邮箱
    private String  userzhucheDate; // 注册日期

    private Integer AccountStatus;  // 账户状态

    private String userName;        // 真实姓名
    private String userArea;        // 地址


    // 验证码
    private String code;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserNiCheng() {
        return userNiCheng;
    }

    public void setUserNiCheng(String userNiCheng) {
        this.userNiCheng = userNiCheng;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getUserPasw() {
        return userPasw;
    }

    public void setUserPasw(String userPasw) {
        this.userPasw = userPasw;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public String getUserzhucheDate() {
        return userzhucheDate;
    }

    public void setUserzhucheDate(String userzhucheDate) {
        this.userzhucheDate = userzhucheDate;
    }

    public Integer getAccountStatus() {
        return AccountStatus;
    }

    public void setAccountStatus(Integer accountStatus) {
        AccountStatus = accountStatus;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserArea() {
        return userArea;
    }

    public void setUserArea(String userArea) {
        this.userArea = userArea;
    }
}
