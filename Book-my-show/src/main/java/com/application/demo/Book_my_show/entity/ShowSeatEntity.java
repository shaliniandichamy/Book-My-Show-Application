package com.application.demo.Book_my_show.entity;

import com.application.demo.Book_my_show.enums.SeatType;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="show_seats")
@Builder
public class ShowSeatEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private boolean isBooked;

    private double price;

    @Enumerated(value=EnumType.STRING)
    private SeatType seatType;

    private String seatNo;

    private Date bookedAt;

    @JoinColumn
    @ManyToOne
    private ShowEntity showEntity;
}

