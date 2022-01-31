package com.example.demo.database;

import com.example.demo.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<Customer, Long> {

}
