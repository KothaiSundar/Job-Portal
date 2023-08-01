package com.example.employeePortal.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.example.employeePortal.entity.Employer;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class JobResponse {
	
	
		private long jobId;
		private String jobTitle;
		private String jobDescription;
		private String jobCompany;
		private long jobSalary;
		
		
		private LocalDateTime jobCreateDatetime;

		private List<String> skills;
		
		private Employer employer;
		
	}

