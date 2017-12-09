package com.xuebusi.springboot.maven.common;

/**
 * Created by SYJ on 2017/8/20.
 */
public class ServiceException extends RuntimeException {

    private Integer code;

    public ServiceException(ViewHint viewHint) {
        super(viewHint.getMessage());
        this.code = viewHint.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
