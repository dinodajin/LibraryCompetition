package com.example.librarycompetition.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorDTO> commonExceptionHandler(Exception e) {
        return new ResponseEntity<>(ErrorDTO.of("알 수 없는 예외입니다."), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ListNotFoundElementException.class)
    protected ResponseEntity<ErrorDTO> noSuchElementExceptionHandler(ListNotFoundElementException lnfe) {
        return new ResponseEntity<>(ErrorDTO.of("리스트에 요소가 존재하지 않습니다."), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    protected ResponseEntity<ErrorDTO> resourceNotFoundExceptionHandler(ResourceNotFoundException rnfe) {
        return new ResponseEntity<>(ErrorDTO.of("요소가 존재하지 않습니다."), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InformationIncorrectException.class)
    protected ResponseEntity<ErrorDTO> informationIncorrectExceptionHandler(InformationIncorrectException infe) {
        return new ResponseEntity<>(ErrorDTO.of("잘못된 정보입니다."), HttpStatus.BAD_REQUEST);
    }

}
