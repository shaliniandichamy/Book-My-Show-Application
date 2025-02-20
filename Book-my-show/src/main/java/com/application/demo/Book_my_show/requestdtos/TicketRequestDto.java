package com.application.demo.Book_my_show.requestdtos;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TicketRequestDto
{
    private int userId;

    private  int showId;

    private List<String> requestedSeats=new ArrayList<>();

}
