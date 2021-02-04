package com.finartz.airlinesystem.exception;

/**
 * @author : Yekta Anil AKSOY
 * @since : 2.02.2021
 **/
public class FullCapacityException extends RuntimeException {

    public FullCapacityException(String msg) {
        super(msg);
    }
}
