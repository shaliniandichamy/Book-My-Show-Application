package com.application.demo.Book_my_show.requestdtos;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class TheaterRequestDto
{

    private String name;

    private String location;

    private int classicSeatsCount;

    private int premiumSeatsCount;

}
