package com.example.demo.dao;

import com.example.demo.entity.user_role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("http://localhost:4200")
@RepositoryRestResource
public interface user_roleRepository extends JpaRepository<user_role, Integer> {

}
