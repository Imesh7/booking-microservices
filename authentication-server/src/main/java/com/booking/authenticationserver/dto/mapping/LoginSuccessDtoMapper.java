package com.booking.authenticationserver.dto.mapping;


import com.booking.authenticationserver.dto.LoginSuccessDto;
import com.booking.authenticationserver.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper
public interface LoginSuccessDtoMapper {

    LoginSuccessDtoMapper INSTANCE = Mappers.getMapper(LoginSuccessDtoMapper.class);

    @Mapping(source = "user.name", target = "name")
     LoginSuccessDto convertDto(User user);

}
