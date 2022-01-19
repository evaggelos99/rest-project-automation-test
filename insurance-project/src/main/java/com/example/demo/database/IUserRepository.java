package com.example.demo.database;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.user.User;

public interface IUserRepository extends JpaRepository<User, Long> {

}
