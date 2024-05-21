package com.hd.ibrarybase.data;

import androidx.databinding.BaseObservable;

/**
 * 作者：yuqi on 2020-05-11 10:14
 * <p>
 * 邮箱：15652357791@163.com
 */
public class BaseObjectBean <T>{

    public int code;
    public String msg;

    public  T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

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

    @Override
    public String toString() {
        return "BaseObjectBean{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
