package com.application.demo.Book_my_show.entity;

import com.application.demo.Book_my_show.enums.Genre;
import com.application.demo.Book_my_show.enums.Language;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="movies")
@Builder
public class MovieEntity
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true,nullable = false)
    private String movieName;

    private double rating;

    private int duration;

    @Enumerated(value = EnumType.STRING)
    private Language language;

    @Enumerated(value = EnumType.STRING)
    private Genre genre;

    //THis is parent wrt to shows
    @OneToMany(mappedBy="movieEntity",cascade = CascadeType.ALL)
    private List<ShowEntity> listOfShows=new ArrayList<>();
}
