package com.github.mayangbo666.enums;

import com.github.mayangbo666.exception.MyException;

public enum FlowTypeEnum {

    deposit_flow(1, "存款"), draw_flow(2, "取款"),
    transfer_in_flow(3, "转入"), transfer_out_flow(4, "转出"), transfer_rollback(5, "转账取消");

    private int k;

    private String v;

    public int getK(){
        return k;
    }

    public String getV(){
        return v;
    }

    private FlowTypeEnum(int k, String v){
        this.k = k;
        this.v = v;
    }

    public static FlowTypeEnum getFlowTypeEnum(int k){

        for (FlowTypeEnum flowTypeEnum : values()) {
            if (flowTypeEnum.getK() == k){
                return flowTypeEnum;
            }
        }

        throw new MyException("流水类型异常");
    }
}
