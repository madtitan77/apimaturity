package com.example.apimaturity.repository;

import com.example.apimaturity.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List; 
import org.springframework.lang.NonNull; 

@Repository
public interface ClientRepo extends JpaRepository<Client, Integer> {
    @NonNull 
    List<Client> findAll();

    List<Client> findByUserId(Integer userId);

    @Query("SELECT up.client FROM UserPermission up WHERE up.user.email = :username")
    List<Client> findClientsByUserPermission(@Param("username") String username);  

    @Query("SELECT c FROM Client c JOIN c.user u WHERE u.email = :username")
    List<Client> findByUser(@Param("username") String username);
}