package com.yakshjethva.usermanagement.dto.Response;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ErrorResponse {
    HttpStatus httpStatus;
    String message;

    public ErrorResponse(HttpStatus httpStatus, String invalidUsernameOrPassword) {
    }
}
