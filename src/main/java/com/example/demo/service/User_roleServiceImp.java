package com.example.demo.service;

import com.example.demo.dao.user_roleRepository;
import com.example.demo.entity.user_role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class User_roleServiceImp implements User_roleService {
    @Autowired
    private user_roleRepository user_roleRepo;
    @Override
    public boolean save(int role_id,int user_id) {
        user_role user_role = new user_role();
        user_role.setRole_id(role_id);
        user_role.setUser_id(user_id);
        user_roleRepo.save(user_role);
        return true;
    }

}
    

