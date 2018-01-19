package com.willkeep.util;

/**
 * <p>Description:</p>
 *
 * @author heng
 * @date 2017-12-17
 */
public class FrontResult<T> extends RequestResult<T> {

    public static <T> FrontResult<T> newSuccess() {
        FrontResult<T> result = new FrontResult<>();
        result.setCode(Constant.SUCCESS);
        return result;
    }

    public static <T> FrontResult<T> newSuccess(T data) {
        FrontResult<T> RequestResult = newSuccess();
        RequestResult.setData(data);
        return RequestResult;
    }

    public static <T> FrontResult<T> newFailure(String message) {
        FrontResult<T> result = new FrontResult<>();
        result.setCode(Constant.FAILED);
        result.setMessage(message);
        return result;
    }

    public static <T> FrontResult<T> newFailure(int code, String message) {
        FrontResult<T> RequestResult = new FrontResult<>();
        RequestResult.setCode(code);
        RequestResult.setMessage(message);
        return RequestResult;
    }
}
