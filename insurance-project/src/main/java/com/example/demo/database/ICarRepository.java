package com.example.demo.database;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.car.Car;

public interface ICarRepository extends JpaRepository<Car, Long>{

}
