package com.booking.authenticationserver.dto;
import lombok.*;


@Data
@Builder
@ToString
public class LoginSuccessDto {
    private String name;
    /*UserDto users;
    TokenResponseDto tokenResponse;


    public LoginSuccessDto(UserDto users, TokenResponseDto tokenResponse) {
        this.users = users;
        this.tokenResponse = tokenResponse;
    }*/
}


