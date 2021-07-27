package com.enums;

/**
 * @Description
 * @Auth chengst
 * @Date Created in  10:43 2021-3-16
 */
public class RetResponse {

    private final static String SUCCESS = ResponseEntity.CM_OK.getMsg();

    //  只返回成功状态的消息
    public static <T> RetResult<T> resultOK() {
        return new RetResult<T>().setCode(ResponseEntity.CM_OK).setMsg(SUCCESS);
    }

    //  返回成功状态及参数
    public static <T> RetResult<T> resultData(T data) {
        return new RetResult<T>().setCode(ResponseEntity.CM_OK).setMsg(SUCCESS).setData(data);
    }


    //  返回失败状态及消息
    public static <T> RetResult<T> resultErr(String message) {
        return new RetResult<T>().setCode(ResponseEntity.CM_FALSE).setMsg(message);
    }

    //  自定义返回状态及消息
    public static <T> RetResult<T> resultCM(int code, String msg) {
        return new RetResult<T>().setCode(code).setMsg(msg);
    }

    //  自定义返回状态及消息和参数
    public static <T> RetResult<T> resultCMData(int code, String msg, T data) {
        return new RetResult<T>().setCode(code).setMsg(msg).setData(data);
    }
}
