package com.github.mayangbo666.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Flow {

    private int id;

    private int cardId;

    private String cardNum;

    private String tradeAmount;

    private int tradeType;

    private Date createTime;
}
