package com.visionmate.springtest.api;

public class TokenDataDTO {

    private String token;

    public TokenDataDTO() {}

    public TokenDataDTO(String token) {
        this.token = "Bearer " + token;
    }

    public String getToken() {
        return token;
    }
}
