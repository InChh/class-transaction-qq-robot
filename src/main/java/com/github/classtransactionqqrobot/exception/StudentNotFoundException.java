package com.github.classtransactionqqrobot.exception;

/**
 * 学生信息未找到异常类
 * @author In_Chh
 */
public class StudentNotFoundException extends RuntimeException{
    public StudentNotFoundException() {
        super();
    }

    public StudentNotFoundException(String message) {
        super(message);
    }
}
