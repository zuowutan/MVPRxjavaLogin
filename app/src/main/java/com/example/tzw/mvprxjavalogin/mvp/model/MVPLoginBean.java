package com.example.tzw.mvprxjavalogin.mvp.model;

/**
 * Created by tzw on 2017/9/21.
 * 登陆请求Bean
 * V层的页面 会输入账号密码
 * 实际开发请按照业务需求封装
 */

public class MVPLoginBean {
    private String userName;
    private String passWord;

    public MVPLoginBean() {
    }

    public MVPLoginBean(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    @Override
    public String toString() {
        return "MVPLoginBean{" +
                "userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                '}';
    }
}
