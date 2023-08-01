package com.example.employeePortal.dto;

import com.example.employeePortal.entity.Resume;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class ApplicantResponse {
	
		private long applicantId;
		private String applicantName;
		private String applicantEmail;
		private long applicantPhNo;

		private Resume resume;
	
}
