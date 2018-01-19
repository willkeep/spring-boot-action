package com.willkeep.util;

/**
 * <p>Description:</p>
 *
 * @author heng
 * @date 2017-12-17
 */
public class ServiceResult<T> extends RequestResult<T> {

    boolean isOk = false;

    public static <T> ServiceResult<T> newSuccess() {
        ServiceResult<T> result = new ServiceResult<>();
        result.setCode(Constant.SUCCESS);
        return result;
    }

    public static <T> ServiceResult<T> newSuccess(T data) {
        ServiceResult<T> RequestResult = newSuccess();
        RequestResult.setData(data);
        return RequestResult;
    }

    public static <T> ServiceResult<T> newFailure() {
        ServiceResult<T> result = new ServiceResult<>();
        result.setCode(Constant.FAILED);
        return result;
    }

    public static <T> ServiceResult<T> newFailure(int code, String message) {
        ServiceResult<T> RequestResult = new ServiceResult<>();
        RequestResult.setCode(code);
        RequestResult.setMessage(message);
        return RequestResult;
    }

    public boolean isOk() {
        return isOk;
    }

    public void setOk(boolean ok) {
        isOk = ok;
    }
}
