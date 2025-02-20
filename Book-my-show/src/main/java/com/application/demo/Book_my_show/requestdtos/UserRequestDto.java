package com.application.demo.Book_my_show.requestdtos;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class UserRequestDto
{

    private String name;

    private int age;


    private String email;


    private String mobile;

    private String address;

}
