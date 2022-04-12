package com.example.demo.dao;

import com.example.demo.entity.UserRegistration;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;


@CrossOrigin("http://localhost:4200")
public interface UserRegistrationRepo extends JpaRepository<UserRegistration, Long> {
    // findByEmail is a method name of JpaRepository

    @Query("select u from UserRegistration u where u.email = ?1")
    UserRegistration findByEmail(String email);
}
