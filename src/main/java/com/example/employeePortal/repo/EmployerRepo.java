package com.example.employeePortal.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.employeePortal.entity.Employer;



public interface EmployerRepo extends JpaRepository<Employer,Long> {

}
