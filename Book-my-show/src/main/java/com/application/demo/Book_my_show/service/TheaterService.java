package com.application.demo.Book_my_show.service;

import com.application.demo.Book_my_show.convertors.TheaterConvertor;
import com.application.demo.Book_my_show.entity.TheaterEntity;
import com.application.demo.Book_my_show.entity.TheaterSeatEntity;
import com.application.demo.Book_my_show.enums.SeatType;
import com.application.demo.Book_my_show.repository.TheaterRepository;
import com.application.demo.Book_my_show.repository.TheaterSeatRepository;
import com.application.demo.Book_my_show.requestdtos.TheaterRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TheaterService
{
    @Autowired
    private TheaterRepository theaterRepository;

    @Autowired
    private TheaterSeatRepository theaterSeatRepository;

    public String addTheater(TheaterRequestDto theaterRequestDto) throws Exception
    {
        //do some empty/blank validation
        if(theaterRequestDto.getName()==null || theaterRequestDto.getLocation()==null)
        {
            throw new Exception("name and location should valid");
        }
        TheaterEntity theaterEntity= TheaterConvertor.convertTheaterRequestDtoToTheaterEntity(theaterRequestDto);
        List<TheaterSeatEntity> theaterSeatEntityList=createTheaterSeats(theaterRequestDto,theaterEntity);
        theaterEntity.setListOfTheaterSeats(theaterSeatEntityList);
        theaterRepository.save(theaterEntity);
        return "theater added successfully";
    }

    private List<TheaterSeatEntity> createTheaterSeats(TheaterRequestDto theaterRequestDto, TheaterEntity theaterEntity)
    {
        int noClassicSeats=theaterRequestDto.getClassicSeatsCount();
        int noPreminmSeats=theaterRequestDto.getPremiumSeatsCount();

        List<TheaterSeatEntity> theaterSeatEntityList=new ArrayList<>();
        //create the classic Seats
        for(int count=1;count<=noClassicSeats;count++)
        {
            TheaterSeatEntity theaterSeatEntity=TheaterSeatEntity.builder()
                    .seatType(SeatType.CLASSIC)
                    .seatNo("C"+count)
                    .theaterEntity(theaterEntity).build();
            theaterSeatEntityList.add(theaterSeatEntity);
        }

        //create the preminum seats
        for(int count=1;count<=noPreminmSeats;count++)
        {
            TheaterSeatEntity theaterSeatEntity=TheaterSeatEntity.builder().seatType(SeatType.PREMIUM).seatNo("P"+count)
                    .theaterEntity(theaterEntity).build();
            theaterSeatEntityList.add(theaterSeatEntity);
        }
        return theaterSeatEntityList;
    }

}
