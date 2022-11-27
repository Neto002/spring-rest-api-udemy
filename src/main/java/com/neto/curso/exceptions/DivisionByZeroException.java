package com.neto.curso.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class DivisionByZeroException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public DivisionByZeroException(String message) {
        super(message);
    }
}
