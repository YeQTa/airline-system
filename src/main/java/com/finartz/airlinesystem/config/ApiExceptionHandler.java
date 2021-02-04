package com.finartz.airlinesystem.config;

import com.finartz.airlinesystem.dto.exception.ExceptionDTO;
import com.finartz.airlinesystem.exception.DataNotFoundException;
import com.finartz.airlinesystem.exception.FullCapacityException;
import java.util.Date;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DataNotFoundException.class)
    public final ResponseEntity<ExceptionDTO> handleDataNotFoundException(
            DataNotFoundException ex, WebRequest request) {
        ExceptionDTO errorDetails =
                new ExceptionDTO(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FullCapacityException.class)
    public final ResponseEntity<ExceptionDTO> handleFullCapacityException(
            FullCapacityException ex, WebRequest request) {
        ExceptionDTO errorDetails =
                new ExceptionDTO(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public final ResponseEntity<ExceptionDTO> handleDataIntegrityViolationException(
            DataIntegrityViolationException ex, WebRequest request) {
        ExceptionDTO errorDetails =
                new ExceptionDTO(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ExceptionDTO> handleAllException(Exception ex, WebRequest request) {
        ExceptionDTO errorDetails =
                new ExceptionDTO(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
