package com.neto.curso.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class UnsupportedMathOperationException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public UnsupportedMathOperationException(String message) {
        super(message);
    }
}