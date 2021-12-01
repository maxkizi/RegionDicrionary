package org.maxkizi.regiondictionary.exception;

import lombok.extern.slf4j.Slf4j;
import org.maxkizi.regiondictionary.dto.Response;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@RestControllerAdvice
public class DictionaryExceptionHandler {
    @ExceptionHandler({RegionNotFoundException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Response handleRegionNotFoundException(RegionNotFoundException ex) {
        log.error("Region not found exception", ex);
        return Response.builder().messages(List.of(ex.getMessage())).build();
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response handleValidationException(MethodArgumentNotValidException ex) {
        log.error("MethodArgumentNotValidException", ex);
        List<String> messages = ex.getBindingResult().getAllErrors().stream().map(e ->
                ((FieldError) e).getField() + ": " + e.getDefaultMessage()
        ).collect(Collectors.toList());
        return Response.builder().messages(messages).build();
    }
}
