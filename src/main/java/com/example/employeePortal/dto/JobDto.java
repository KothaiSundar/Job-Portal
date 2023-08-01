package com.example.employeePortal.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.example.employeePortal.entity.Employer;
import com.example.employeePortal.entity.JobApplication;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JobDto {
	private long jobId;
	private String jobTitle;
	private String jobDescription;
	private String jobCompany;
	private long jobSalary;
}
