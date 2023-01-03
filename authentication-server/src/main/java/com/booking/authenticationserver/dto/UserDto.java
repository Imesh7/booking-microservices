package com.booking.authenticationserver.dto;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class UserDto {
    Long id;
    String name;
    String email;

}
