package com.southwind.entity;

import java.util.List;

public class ReaderVO {
    private int code;
    private String msg;
    private int count;
    private List<Reader> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Reader> getData() {
        return data;
    }

    public void setData(List<Reader> data) {
        this.data = data;
    }
}
