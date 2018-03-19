package com.ender.util.exception;

/**
 * 验证码提交队列异常，用来获取等待时间
 */
public class VerifyCodeException extends RuntimeException {
    private long waitTime;
    private int code;

    public VerifyCodeException(int code, long waitTime) {
        super();
        this.waitTime = waitTime;
        this.code = code;
    }

    public long getWaitTime() {
        return waitTime;
    }

    public int getCode() {
        return code;
    }
}
