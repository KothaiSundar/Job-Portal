package com.example.employeePortal.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
public class JobApplicantionByJobDTO {
	
		private long jobApplicationId;
		private LocalDateTime jobApplicationDateTime;
		private ApplicantDto applicantDto;
		

}
