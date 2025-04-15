package com.example.codionbe.global.common.success;

import org.springframework.http.HttpStatus;

public interface SuccessCode {
    HttpStatus getStatus();
    String getCode();
    String getMessage();
}
