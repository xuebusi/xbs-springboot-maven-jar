package com.xuebusi.springboot.maven.common;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Created by SYJ on 2017/5/19.
 */
@JsonSerialize(include= JsonSerialize.Inclusion.NON_NULL)
public class ResultModel<T> {

    private Integer code;
    private String taskId ;
    private String message;
    private String time;
    private T data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResultModel{" +
                "code=" + code +
                ", taskId='" + taskId + '\'' +
                ", message='" + message + '\'' +
                ", time='" + time + '\'' +
                ", data=" + data +
                '}';
    }
}
