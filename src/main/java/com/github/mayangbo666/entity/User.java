package com.github.mayangbo666.entity;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private Integer id;
    private String loginCode;
    private String userName;
    private String password;
    private Date createTime;

    public User(){

    }

    public User(String loginCode, String userName, String password, Date createTime) {
        this.loginCode = loginCode;
        this.userName = userName;
        this.password = password;
        this.createTime = createTime;
    }
}
