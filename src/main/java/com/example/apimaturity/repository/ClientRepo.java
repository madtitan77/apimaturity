package com.example.apimaturity.repository;

import com.example.apimaturity.model.Client;
import com.example.apimaturity.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import org.springframework.lang.NonNull;

@Repository
public interface ClientRepo extends JpaRepository<Client, Integer> {
    @NonNull
    List<Client> findAll();
    List<Client> findByCreator(User creator);
    List<Client> findByUsersWithAccess(User user);
}