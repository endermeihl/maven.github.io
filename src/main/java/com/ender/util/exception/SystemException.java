package com.ender.util.exception;


/**
 * 框架异常基类。
 *
 * @author xmpp
 */
public class SystemException extends RuntimeException {

    public SystemException(String message) {
        super(message);
    }

    public SystemException(String message, Exception e) {
        super(message,e);
    }

    public SystemException() {
    }


    public SystemException(Throwable t) {
        super(t);
    }

    public SystemException(String msg, Throwable t) {
        super(msg, t);
    }
}
