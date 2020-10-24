package com.github.openboot.exception;

public class QuestionNotFoundException extends RuntimeException {

    private final String name;

    public QuestionNotFoundException(String name) {
        super("Question by name " + name + " is not found");
        this.name = name;
    }
}
