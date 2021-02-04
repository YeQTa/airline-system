package com.finartz.airlinesystem.dto.exception;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : Yekta Anil AKSOY
 * @since : 3.02.2021
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionDTO {

    private Date timestamp;
    private String message;
    private String details;
}
