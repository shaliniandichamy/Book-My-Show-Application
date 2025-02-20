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
@Table(name="users")
@Builder
public class UserEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private int age;

    @Column(unique=true,nullable = false)
    private String email;

    @Column(unique = true,nullable = false)
    private String mobile;

    private String address;

    @OneToMany(mappedBy="userEntity",cascade=CascadeType.ALL)
    private List<TicketEntity> bookedTickets=new ArrayList<>();
}
