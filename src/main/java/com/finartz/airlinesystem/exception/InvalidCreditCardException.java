package com.finartz.airlinesystem.exception;

/**
 * @author : Yekta Anil AKSOY
 * @since : 4.02.2021
 **/
public class InvalidCreditCardException extends RuntimeException {

    public InvalidCreditCardException(String msg) {
        super(msg);
    }
}
