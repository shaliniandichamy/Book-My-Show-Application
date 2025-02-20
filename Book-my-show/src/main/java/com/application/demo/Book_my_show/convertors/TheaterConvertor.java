package com.application.demo.Book_my_show.convertors;

import com.application.demo.Book_my_show.entity.TheaterEntity;
import com.application.demo.Book_my_show.requestdtos.TheaterRequestDto;

public class TheaterConvertor
{
    public static TheaterEntity convertTheaterRequestDtoToTheaterEntity(TheaterRequestDto theaterRequestDto)
    {
        TheaterEntity theaterEntity= TheaterEntity.builder().name(theaterRequestDto.getName())
                .location(theaterRequestDto.getLocation()).build();
        return theaterEntity;
    }
}
