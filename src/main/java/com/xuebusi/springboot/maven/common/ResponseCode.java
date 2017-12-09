package com.xuebusi.springboot.maven.common;

/**
 * Created by xuebusi.com on 2017/5/19.
 */
public enum ResponseCode {

    SUCCESS(200,"SUCCESS"),
    ERROR(414,"ERROR");

    private final int code;
    private final String desc;


    ResponseCode(int code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public int getCode(){
        return code;
    }
    public String getDesc(){
        return desc;
    }

}
