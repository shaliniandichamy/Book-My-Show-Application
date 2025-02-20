package com.application.demo.Book_my_show.service;

import com.application.demo.Book_my_show.convertors.ShowConvertor;
import com.application.demo.Book_my_show.entity.*;
import com.application.demo.Book_my_show.enums.SeatType;
import com.application.demo.Book_my_show.enums.ShowType;
import com.application.demo.Book_my_show.repository.MovieRepository;
import com.application.demo.Book_my_show.repository.ShowRepository;
import com.application.demo.Book_my_show.repository.TheaterRepository;
import com.application.demo.Book_my_show.requestdtos.ShowRequestDto;
import com.application.demo.Book_my_show.requestdtos.TheaterRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ShowService
{
    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private TheaterRepository theaterRepository;


    public String addShow(ShowRequestDto showRequestDto)
    {
       ShowEntity showEntity= ShowConvertor.convertShowRequestDtoToShowEntity(showRequestDto);

       int movieId=showRequestDto.getMovie_id();
       int theaterId=showRequestDto.getTheater_id();

        MovieEntity movieEntity=movieRepository.findById(movieId).get();
        TheaterEntity theaterEntity=theaterRepository.findById(theaterId).get();

        showEntity.setMovieEntity(movieEntity);
        showEntity.setTheaterEntity(theaterEntity);

        List<ShowSeatEntity> showSeatEntityList=createShowSeatEntity(showRequestDto,showEntity);
        showEntity.setListOfShowSeats(showSeatEntityList);

        showEntity.setShowDateTime(showRequestDto.getShowDateTime());
        showEntity.setCreatedOn(new Date());
        showEntity.setUpdateOn(new Date());

        showEntity=showRepository.save(showEntity);

        movieEntity.getListOfShows().add(showEntity);
        theaterEntity.getListOfShows().add(showEntity);

        movieRepository.save(movieEntity);
        theaterRepository.save(theaterEntity);

        return "show has been sucessfully added";
    }


    private List<ShowSeatEntity> createShowSeatEntity(ShowRequestDto showRequestDto, ShowEntity showEntity)
    {
        //create a showseat entity
        TheaterEntity theaterEntity=showEntity.getTheaterEntity();

        List<TheaterSeatEntity> theaterSeatEntityList=theaterEntity.getListOfTheaterSeats();
        List<ShowSeatEntity> showSeatEntityList=new ArrayList<>();
        for(TheaterSeatEntity theaterSeatEntity:theaterSeatEntityList)
        {
            ShowSeatEntity showSeatEntity=new ShowSeatEntity();
            showSeatEntity.setSeatNo(theaterSeatEntity.getSeatNo());
            showSeatEntity.setSeatType(theaterSeatEntity.getSeatType());

            if(theaterSeatEntity.getSeatType().equals(SeatType.CLASSIC))
                  showSeatEntity.setPrice(showRequestDto.getClassicSeatPrice());
            else
                showSeatEntity.setPrice(showRequestDto.getPremiumSeatPrice());

            showSeatEntity.setBooked(false);
            showSeatEntity.setShowEntity(showEntity);

            showSeatEntityList.add(showSeatEntity);

        }
        return showSeatEntityList;
    }

    public List<ShowEntity> getShowByShowType(ShowType showType)
    {
        List<ShowEntity> showEntities =showRepository.getShowByShowType(showType);
        return showEntities;
    }
}















