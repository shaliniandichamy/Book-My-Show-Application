package com.application.demo.Book_my_show.entity;

import com.application.demo.Book_my_show.enums.SeatType;
import jakarta.persistence.*;
import lombok.*;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="theater_seats")
public class TheaterSeatEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(value=EnumType.STRING)

    private SeatType seatType;

    private String seatNo;

    @JoinColumn
    @ManyToOne
    private TheaterEntity theaterEntity;


}
