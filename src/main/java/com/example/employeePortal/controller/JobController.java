package com.example.employeePortal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.employeePortal.dto.JobDto;
import com.example.employeePortal.dto.JobResponse;
import com.example.employeePortal.entity.Job;

import com.example.employeePortal.service.JobService;
import com.example.employeePortal.util.ResponseStructure;

@RestController
@RequestMapping("/job")
public class JobController {
	@Autowired 
	private JobService jobService;
	@PostMapping
	public ResponseEntity<ResponseStructure<JobResponse>> addJob(@RequestBody JobDto jobDto, @RequestParam long employerId, @RequestParam String[] skills)
	
	{
		return jobService.addJob(jobDto,employerId,skills);
	}
	@GetMapping
	public ResponseEntity<ResponseStructure<JobResponse>> getJob( @RequestParam long jobId)
	
	{
		return jobService.getJob(jobId);
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<Job>> updateJob( @RequestParam long jobId,@RequestBody JobDto jobDto)
	
	{ 
		return jobService.updateJob(jobId,jobDto);
		
	}
	@GetMapping("/skill")
	public ResponseEntity<ResponseStructure<List<JobResponse>>> getJobsBySkill( @RequestParam long skillId)
	
	{ 
		return jobService.getJobsBySkill(skillId);
		
	}
	@DeleteMapping
	public ResponseEntity<ResponseStructure<Job>> deleteJob( @RequestParam long jobId)
	
	{ 
		return jobService.deleteJob(jobId);
		
	}
}
