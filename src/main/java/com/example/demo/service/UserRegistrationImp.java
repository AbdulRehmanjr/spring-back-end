package com.example.demo.service;

import java.util.List;

import com.example.demo.dao.UserRegistrationRepo;
import com.example.demo.entity.UserRegistration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserRegistrationImp implements UserRegistrationService {

    @Autowired
    private UserRegistrationRepo userRegistrationRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public UserRegistration save(UserRegistration userRegistration) {
        System.out.println("hashing and saving");
        // hashing the password
       String password = passwordEncoder.encode(userRegistration.getPassword());
      userRegistration.setPassword(password);
        return userRegistrationRepo.save(userRegistration);
    }
    @Override
    public UserRegistration update(UserRegistration userRegistration) {
        
        return userRegistrationRepo.save(userRegistration);
    }

    @Override
    public void delete(Long id) {
        userRegistrationRepo.deleteById(id);
        
    }

    @Override
    public UserRegistration findById(Long id) {

        return userRegistrationRepo.findById(id).get();
    }

    @Override
    public UserRegistration findByEmail(String email) {
        
        return userRegistrationRepo.findByEmail(email);
        
    }
    @Override
    public List<UserRegistration> findAll() {
        
        return userRegistrationRepo.findAll();
    }
    
}
