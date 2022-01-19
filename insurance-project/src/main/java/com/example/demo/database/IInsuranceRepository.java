package com.example.demo.database;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.insurance.Insurance;

public interface IInsuranceRepository extends JpaRepository<Insurance, Long> {

}
