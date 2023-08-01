package com.example.employeePortal.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.employeePortal.entity.Project;

public interface ProjectRepo extends JpaRepository<Project,Long>{

}
