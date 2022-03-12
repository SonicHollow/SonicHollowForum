package com.sonichollow.forum.service.ex;

/**
 * 数据在插入过程中产生的异常
 */
public class InsertException extends ServiceException{

    // ctrl + o --- override methods...
    // 快速创建代码

    public InsertException() {
        super();
    }

    public InsertException(String message) {
        super(message);
    }

    public InsertException(String message, Throwable cause) {
        super(message, cause);
    }

    public InsertException(Throwable cause) {
        super(cause);
    }

    protected InsertException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
