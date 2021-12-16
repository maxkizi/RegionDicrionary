package org.maxkizi.regiondictionary.exception.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    BOOK_NOT_FOUND_EXCEPTION  ("Регион не найден"),
    APPLICATION_USER_NOT_FOUND_EXCEPTION ("Пользователь не найден");
    private final String message;
}
