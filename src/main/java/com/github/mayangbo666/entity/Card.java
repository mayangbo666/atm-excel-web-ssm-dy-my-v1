package com.github.mayangbo666.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Card {

    private Integer id;
    private Integer userId;
    private String cardNum;
    private String wallet;
    private String password;
    private Date createTime;

}
