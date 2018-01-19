package com.willkeep.util;

import java.io.Serializable;

/**
 * <p>Description:</p>
 *
 * @author heng
 * @date 2017-12-17
 */
public class RequestResult<T> implements Serializable {

    private int code = Constant.FAILED;
    private String message;
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
