package com.application.demo.Book_my_show.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="tickets")
public class TicketEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String moviename;

    private Date showDateTime;

    private double totalAmount;

    private String bookedSeats;

    private String theatername;

    private String ticketId= UUID.randomUUID().toString();

    @JoinColumn
    @ManyToOne
    private UserEntity userEntity;

    //this is child wrt show
    @JoinColumn
    @ManyToOne
    private ShowEntity showEntity;
}
