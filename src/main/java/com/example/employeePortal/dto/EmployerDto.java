package com.example.employeePortal.dto;



import java.util.List;

import org.springframework.stereotype.Component;

import com.example.employeePortal.entity.Job;


import lombok.Getter;
import lombok.Setter;


@Component
@Getter
@Setter
public class EmployerDto {
	private long employerId;
	private String employerName;
	private String employerEmail;
	

	private List<Job> jobs;
}
