package com.dong.exception;

import com.dong.ResultCodeEnum;
import com.sun.org.apache.bcel.internal.classfile.Code;
import lombok.Data;

import java.lang.reflect.Member;
import java.security.MessageDigest;

/**
 * 自定义全局异常类
 */
@Data
public class GuiguException extends RuntimeException{

    private Integer code; //状态码
    private String message; //描述信息

    /**
     * 通过状态码和错误消息创建异常对象
     * @param code
     * @param message
     */
    public GuiguException(Integer code,String message) {
        super(message); //调用父类中的message
        this.code = code;
        this.message = message;
    }

    /**
     * 接收枚举类型对象
     * @param resultCodeEnum
     */
    public GuiguException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
        this.message = resultCodeEnum.getMessage();
    }
    public String toString() {
        return "GuliException{" +
                "code=" + code +
                ", message=" + this.getMessage() +
                '}';
    }
 }
