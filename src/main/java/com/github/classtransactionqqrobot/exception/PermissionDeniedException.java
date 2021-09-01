package com.github.classtransactionqqrobot.exception;

/**
 * 权限异常类
 * @author In_Chh
 */
public class PermissionDeniedException extends Exception{
    public PermissionDeniedException() {
        super("权限不足");
    }

    public PermissionDeniedException(String message) {
        super(message);
    }
}
