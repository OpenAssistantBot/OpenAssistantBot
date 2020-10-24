package com.github.openboot.exception;

import lombok.Getter;

@Getter
public class QuestionNotFoundException extends RuntimeException {

    private final String name;

    public QuestionNotFoundException(String name) {
        super("The question by a name '" + name + "' is not found.");
        this.name = name;
    }
}
