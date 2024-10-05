package org.example.validation;

public class InputValidator {
    private InputValidator() {

    }
    public static void validateInput(String[] args) {
        if(args.length != 2) {
            throw new IllegalArgumentException("please provide start and end node ids");
        }
    }
}
