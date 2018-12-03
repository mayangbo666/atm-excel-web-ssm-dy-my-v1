package com.github.mayangbo666.vo;

import lombok.Getter;

public class AjaxVO {
    @Getter
    private boolean success;
    @Getter
    private String message;
    @Getter
    private Object data;

    private AjaxVO(){

    }

    private AjaxVO(boolean success, String message, Object data){
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public static AjaxVO success(){
        return new AjaxVO(true, null, null);
    }

    public static AjaxVO success(Object data){
        return new AjaxVO(true, null, data);
    }

    public static AjaxVO failed(String message){
        return new AjaxVO(false, message, null);
    }
}
