package com.example.demo.database;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.customer.Customer;

public interface IUserRepository extends JpaRepository<Customer, Long> {

}
