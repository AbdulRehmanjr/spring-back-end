package com.example.demo.service;

import java.util.List;


import com.example.demo.entity.UserRegistration;

public interface UserRegistrationService {

    UserRegistration save(UserRegistration userRegistration);
    UserRegistration update(UserRegistration userRegistration);
    void delete(Long id);
    UserRegistration findById(Long id);
    List<UserRegistration> findAll();
    UserRegistration findByEmail(String email);
}
