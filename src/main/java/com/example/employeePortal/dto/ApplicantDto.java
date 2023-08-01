package com.example.employeePortal.dto;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
@Component
@Getter
@Setter
public class ApplicantDto {
	private long applicantId;
	private String applicantName;
	private String applicantEmail;
	private long applicantPhNo;
}
