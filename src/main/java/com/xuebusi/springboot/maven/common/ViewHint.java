package com.xuebusi.springboot.maven.common;


/**
 * Created by SYJ on 2017/5/19.
 */
public enum ViewHint {
    Success("成功", ResponseCode.SUCCESS.getCode()),
    Fail("失败", ResponseCode.ERROR.getCode()),
    KeyNotIsNull("ID不能为空", 400);

    private  String message;
    private  int code;

    ViewHint(String message, int code) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }
}
