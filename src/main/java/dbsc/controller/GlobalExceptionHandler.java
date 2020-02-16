package dbsc.controller;

import dbsc.exception.HttpException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpException.class)
    public ResponseEntity<Object> handleWebException(HttpServletResponse response, HttpException ex) {
        log.error(ex.getMessage());
        return ResponseEntity.status(ex.getHttpStatus())
                .body(new ErrorMessage(ex.getMessage()));

    }

    @Data
    @AllArgsConstructor
    private static class ErrorMessage  {
        private String message;
    }
}
