package com.e_commerce.Exceptions;

public class AuthenticationFailException extends IllegalArgumentException{
    public AuthenticationFailException(String msg){
        super(msg);
    }
}
