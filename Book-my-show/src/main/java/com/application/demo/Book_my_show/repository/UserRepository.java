package com.application.demo.Book_my_show.repository;


import com.application.demo.Book_my_show.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Integer>
{

}
