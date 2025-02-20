package com.application.demo.Book_my_show.entity;

import com.application.demo.Book_my_show.enums.ShowType;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="shows")
@Builder
public class ShowEntity
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date showDateTime;

    private Date createdOn;

    private Date updateOn;

    @Enumerated(value=EnumType.STRING)
    private ShowType showType;

    //THis is parent wrt to ticket
    @OneToMany(mappedBy="showEntity",cascade = CascadeType.ALL)
    private List<TicketEntity> listOfBookedTickets=new ArrayList<>();

    @JoinColumn
    @ManyToOne
    private MovieEntity movieEntity;

    @JoinColumn
    @ManyToOne
    private TheaterEntity theaterEntity;

    //THis is parent wrt to showseat
    @OneToMany(mappedBy="showEntity",cascade = CascadeType.ALL)
    private List<ShowSeatEntity> listOfShowSeats=new ArrayList<>();

}
