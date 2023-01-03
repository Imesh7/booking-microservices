package com.booking.authenticationserver.dto;

import lombok.Data;

@Data
public class TokenResponseDto {
    TokenDto access_token;
    TokenDto refresh_token;
}
