package org.maxkizi.regiondictionary.exception;

import org.maxkizi.regiondictionary.exception.util.ErrorCode;

public class RegionNotFoundException extends RuntimeException {
    public RegionNotFoundException(){
        super(ErrorCode.BOOK_NOT_FOUND_EXCEPTION.getMessage());
    }
}
