package com.example.employeePortal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.employeePortal.entity.JobApplication;
import com.example.employeePortal.service.JobApplicationService;
import com.example.employeePortal.util.ResponseStructure;

@RestController
@RequestMapping("/apply")
public class JobApplicationController {

	@Autowired
	private JobApplicationService applicationService;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<JobApplication>> createJobApplication(
			@RequestParam long applicantId, @RequestParam long jobId){
		return applicationService.createJobApplication(applicantId, jobId);
	}
	

	@GetMapping("/applicant")
	public ResponseEntity<ResponseStructure<List<JobApplication>>> getJobApplicationsByApplicant(
			@RequestParam long applicantId){
		return applicationService.getJobApplicationsByApplicant(applicantId);
	}
	
	
	@GetMapping("/job")
	public ResponseEntity<ResponseStructure<List<JobApplication>>> getJobApplicationsByJob(
			@RequestParam long jobId){
		return applicationService.getJobApplicationsByJob(jobId);
	}

}
