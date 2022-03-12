package com.sonichollow.forum.util;

import java.io.Serializable;

/**
 * Json 格式的数据进行响应
 */
public class JsonResult<E> implements Serializable {
    private Integer state;  //状态码
    private String message; //描述信息
    private E data;         //泛型数据类型

    public JsonResult() {
    }

    public JsonResult(Integer state) {
        this.state = state;
    }

    public JsonResult(Throwable e) {
        this.message = e.getMessage();
    }

    public JsonResult(Integer state, E data) {
        this.state = state;
        this.data = data;
    }

    // get/set 方法

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }
}
