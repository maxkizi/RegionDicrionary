package org.maxkizi.regiondictionary.exception;

import org.maxkizi.regiondictionary.exception.util.ErrorCode;

public class ApplicationUserNotFoundException extends RuntimeException {
    public ApplicationUserNotFoundException() {
        super(ErrorCode.APPLICATION_USER_NOT_FOUND_EXCEPTION.getMessage());
    }
}
