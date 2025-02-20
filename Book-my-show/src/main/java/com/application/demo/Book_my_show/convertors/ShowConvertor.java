package com.application.demo.Book_my_show.convertors;

import com.application.demo.Book_my_show.entity.ShowEntity;
import com.application.demo.Book_my_show.requestdtos.ShowRequestDto;


public class ShowConvertor
{

    public static ShowEntity convertShowRequestDtoToShowEntity(ShowRequestDto showRequestDto)
    {
        ShowEntity showEntity= ShowEntity.builder().showDateTime(showRequestDto.getShowDateTime())
                .showType(showRequestDto.getShowType()).build();
        return showEntity;
    }
}
