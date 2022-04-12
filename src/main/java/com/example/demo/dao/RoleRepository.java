package com.example.demo.dao;

import com.example.demo.entity.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("http://localhost:4200")
@RepositoryRestResource
public interface RoleRepository  extends JpaRepository<Role, Long> {

}
    

