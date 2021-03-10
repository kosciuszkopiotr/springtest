package com.visionmate.springtest.api;

import java.util.UUID;

public class TokenDataDTO {

    public String token;

    public TokenDataDTO() {}
    public TokenDataDTO(String token) {
        this.token = "Bearer " + token;
    }

}
