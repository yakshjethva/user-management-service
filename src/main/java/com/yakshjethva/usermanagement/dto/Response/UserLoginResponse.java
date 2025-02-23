package com.yakshjethva.usermanagement.dto.Response;

import lombok.Data;

@Data
public class UserLoginResponse {
    private String email;
    private String token;

    public UserLoginResponse(String email, String token) {
        this.email=email;
        this.token=token;
    }
}
