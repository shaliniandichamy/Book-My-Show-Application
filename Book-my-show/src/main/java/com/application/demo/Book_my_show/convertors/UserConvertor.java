package com.application.demo.Book_my_show.convertors;

import com.application.demo.Book_my_show.entity.UserEntity;
import com.application.demo.Book_my_show.requestdtos.UserRequestDto;

public class UserConvertor
{
    public static UserEntity convertUserRequestDtoToUserEntity(UserRequestDto userRequestDto)
    {
        UserEntity userEntity= UserEntity.builder().name(userRequestDto.getName()).age(userRequestDto.getAge())
                .email(userRequestDto.getEmail()).mobile(userRequestDto.getMobile()).address(userRequestDto.getAddress()).build();
        return userEntity;
    }
}
