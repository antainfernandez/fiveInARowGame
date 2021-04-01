package com.antainfernandez.fiveInARowGame.controller;

import com.antainfernandez.fiveInARowGame.exception.InvalidGameException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class InvalidGameAdvice {

    @ResponseBody
    @ExceptionHandler(InvalidGameException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String invalidGameExceptionHandler(InvalidGameException ex) {
        return ex.getMessage();
    }
}