package com.github.classtransactionqqrobot.exception;


/**
 * 未找到学生对象异常类
 * @author In_Chh
 */
public class StudentNotFoundException extends RuntimeException{
    public StudentNotFoundException(String message) {
        super(message);
    }
}
