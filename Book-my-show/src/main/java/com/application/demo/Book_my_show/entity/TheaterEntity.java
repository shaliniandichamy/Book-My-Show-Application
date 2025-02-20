package com.application.demo.Book_my_show.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="theaters")
@Builder
public class TheaterEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String location;

    //THis is parent wrt to shows
    @OneToMany(mappedBy="theaterEntity",cascade = CascadeType.ALL)
    private List<ShowEntity> listOfShows=new ArrayList<>();

    //THis is parent wrt to theater entity
    @OneToMany(mappedBy="theaterEntity",cascade = CascadeType.ALL)
    private List<TheaterSeatEntity> listOfTheaterSeats=new ArrayList<>();

}
