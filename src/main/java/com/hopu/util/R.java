package com.hopu.util;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;


@Data
public class R {


    private Boolean success;


    private Integer code;


    private String message;


    private Map<String, Object> data = new HashMap<String, Object>();

    /**
     * 把构造方法私有
     */
    private R() {
    }

    /**
     * 成功静态方法
     */
    public static com.hopu.util.R ok() {
        com.hopu.util.R r = new com.hopu.util.R();
        r.setSuccess(true);
        r.setCode(ResultCode.SUCCESS);
        r.setMessage("成功");
        return r;
    }

    /**
     * 失败静态方法
     */
    public static com.hopu.util.R error() {
        com.hopu.util.R r = new com.hopu.util.R();
        r.setSuccess(false);
        r.setCode(ResultCode.ERROR);
        r.setMessage("失败");
        return r;
    }

    public com.hopu.util.R success(Boolean success) {
        this.setSuccess(success);
        return this;
    }

    public com.hopu.util.R message(String message) {
        this.setMessage(message);
        return this;
    }

    public com.hopu.util.R code(Integer code) {
        this.setCode(code);
        return this;
    }

    public com.hopu.util.R data(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

    public com.hopu.util.R data(Map<String, Object> map) {
        this.setData(map);
        return this;
    }
}
