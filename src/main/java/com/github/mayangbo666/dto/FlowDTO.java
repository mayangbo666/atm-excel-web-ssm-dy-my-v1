package com.github.mayangbo666.dto;

import lombok.Data;

/**
 * FlowDTO 用于数据传输的对象, 把entity字段类型进行了必要的转换
 */
@Data
public class FlowDTO {

    private int id;

    private int cardId;

    private String cardNum;

    private String tradeAmount;

    private String tradeType;//

    private String createTime;//
}
