package com.example.employeePortal.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.employeePortal.entity.Resume;

public interface ResumeRepo extends JpaRepository<Resume,Long>{

}
