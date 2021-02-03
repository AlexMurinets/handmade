package com.example.handmadeBackend.exception.message;

public class ProductException extends RuntimeException{
    public ProductException(String message){
        super(message);
    }
}