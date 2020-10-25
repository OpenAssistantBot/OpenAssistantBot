//package com.github.openassistant.handler;
//
//import com.github.openassistant.dto.ErrorDto;
//import com.github.openassistant.exception.QuestionNotFoundException;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
//@RestControllerAdvice
//public class CustomExceptionHandler {
//
//    @ExceptionHandler(QuestionNotFoundException.class)
//    public ResponseEntity<?> handleQuestionNotFoundException(QuestionNotFoundException e) {
//        return new ResponseEntity<>(ErrorDto.builder()
//                .title("QuestionNotFoundException")
//                .code(404)
//                .message(e.getMessage())
//                .build(), HttpStatus.NOT_FOUND);
//    }
//
//    @ExceptionHandler(RuntimeException.class)
//    public ResponseEntity<?> handleRuntimeException(RuntimeException e) {
//        return new ResponseEntity<>(ErrorDto.builder()
//                .title(e.getClass().getSimpleName())
//                .code(500)
//                .message(e.getMessage())
//                .build(), HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//}
