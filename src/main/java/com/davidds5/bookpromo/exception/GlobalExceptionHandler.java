package com.davidds5.bookpromo.exception;

import java.time.Instant;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<StandardError> illegalArgument(IllegalArgumentException e, HttpServletRequest request) {
        StandardError err = new StandardError();
        err.setTimestamp(Instant.now());
        err.setStatus(HttpStatus.BAD_REQUEST.value());
        err.setError("Illegal Argument");
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ProblemDetail> methodArgumentNotValid(MethodArgumentNotValidException e,
            HttpServletRequest request) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle("Um ou mais campos contem dados invalidos, Corrija e tente novamente");
        problemDetail.setDetail("Um ou mais campos contêm dados inválidos. Verifique os erros detalhados abaixo.");
        problemDetail.setStatus(HttpStatus.BAD_REQUEST.value());

        Map<String, String> errMap = e.getBindingResult().getFieldErrors()
                .stream()
                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
        problemDetail.setProperty("errors", errMap);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problemDetail);
    }

}