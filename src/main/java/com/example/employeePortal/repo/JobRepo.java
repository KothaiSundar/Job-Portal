package com.example.employeePortal.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.employeePortal.entity.Job;
import com.example.employeePortal.entity.JobApplication;


public interface JobRepo extends JpaRepository<Job,Long> {
	@Query(value= "select a.jobApplications from Job a where a.jobId=?1")
	public Optional<List<JobApplication>> getJobApplications(long jobId);
}
