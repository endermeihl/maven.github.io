package com.ender.util.exception;

/**
 * 业务异常
 */
public class BusinessException extends RuntimeException {
    private int errorcode;
    private Object[] args;

    public BusinessException(Throwable t, int errorcode) {
        super(t);
        this.errorcode = errorcode;
    }

    public BusinessException(int errorcode) {
        super();
        this.errorcode = errorcode;
    }

    public BusinessException(int errorcode, Object... args) {
        super();
        this.errorcode = errorcode;
        this.args = args;
    }

    public BusinessException(Throwable t, int errorcode, Object... args) {
        super(t);
        this.errorcode = errorcode;
        this.args = args;
    }

    public int getErrorcode() {
        return errorcode;
    }

    public Object[] getArgs() {
        return args;
    }
}
