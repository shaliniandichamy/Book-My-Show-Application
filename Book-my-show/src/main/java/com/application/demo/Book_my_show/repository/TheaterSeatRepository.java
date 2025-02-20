package com.application.demo.Book_my_show.repository;

import com.application.demo.Book_my_show.entity.TheaterEntity;
import com.application.demo.Book_my_show.entity.TheaterSeatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheaterSeatRepository extends JpaRepository<TheaterSeatEntity,Integer>
{

}
