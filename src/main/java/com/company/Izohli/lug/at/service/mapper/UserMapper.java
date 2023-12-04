package com.company.Izohli.lug.at.service.mapper;

import com.company.Izohli.lug.at.dto.requestDto.RequestUserDto;
import com.company.Izohli.lug.at.dto.responseDto.UserDto;
import com.company.Izohli.lug.at.module.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(componentModel = "spring")
public abstract class UserMapper {

    @Autowired
    protected PasswordEncoder passwordEncoder;





    @Mapping(target = "code",expression = "java(String.valueOf(0000))")
    @Mapping(target = "enabled", expression = "java(false)")
    @Mapping(target = "password", expression = "java(this.passwordEncoder.encode(dto.getPassword()))")
    public abstract User toEntity(RequestUserDto dto);

    public abstract User toEntityResponseDto(UserDto dto);


    public abstract UserDto toDto(User user);
}
