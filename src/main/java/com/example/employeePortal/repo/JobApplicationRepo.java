package com.example.employeePortal.repo;

import org.springframework.data.jpa.repository.JpaRepository;


import com.example.employeePortal.entity.JobApplication;

public interface JobApplicationRepo extends JpaRepository<JobApplication,Long>{

}
