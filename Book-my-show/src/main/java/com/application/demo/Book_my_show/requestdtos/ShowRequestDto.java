package com.application.demo.Book_my_show.requestdtos;

import com.application.demo.Book_my_show.enums.ShowType;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@Data
public class ShowRequestDto
{
    private Date showDateTime;

    private ShowType showType;

    private int movie_id;

    private int theater_id;

    private double classicSeatPrice;

    private double premiumSeatPrice;
}
