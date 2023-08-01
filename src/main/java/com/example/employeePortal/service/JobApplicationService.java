package com.example.employeePortal.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.employeePortal.dao.ApplicantDao;
import com.example.employeePortal.dao.JobApplicationDao;
import com.example.employeePortal.dao.JobDao;
import com.example.employeePortal.dto.JobApplicationByApplicantDTO;
import com.example.employeePortal.dto.JobApplicantionByJobDTO;
import com.example.employeePortal.dto.JobDto;
import com.example.employeePortal.dto.ApplicantDto;
import com.example.employeePortal.entity.Applicant;
import com.example.employeePortal.entity.Job;
import com.example.employeePortal.entity.JobApplication;
import com.example.employeePortal.exception.AlreadyAppliedToJobException;
import com.example.employeePortal.exception.ApplicantNotFoundByIdException;
import com.example.employeePortal.exception.JobApplicationNotFoundByIdException;
import com.example.employeePortal.exception.JobNotFoundByIdException;

import com.example.employeePortal.util.ResponseStructure;


@Service
public class JobApplicationService
{
	
@Autowired
private JobApplicationDao jobapplicationDao;
@Autowired
private ApplicantDao applicantDao;	
@Autowired
private JobDao jobDao;

@Autowired
private ModelMapper modelMapper;

public ResponseEntity<ResponseStructure<JobApplication>> createJobApplication(long applicantId,long jobId)

{
	Applicant applicant=applicantDao.getApplicant(applicantId);
	if(applicant!=null)
	{
		Job job=jobDao.getJob(jobId);
		if(job!=null) {
			
			//checking if applicant already applied to job or not
			List<JobApplication> applications=applicant.getJobApplications();
			for(JobApplication a:applications)
			{
				if(a.getJob().equals(job))
				{
					throw new AlreadyAppliedToJobException("Failed to apply job Applicantion ");
				}
			}
			JobApplication application = new  JobApplication();
			application.setJobApplicationDateTime(LocalDateTime.now());
			application.setJob(job);
			application.setApplicant(applicant);
			
			// saving the jobApplication object
			application = jobapplicationDao.createJobApplication(application);
			
			// setting and updating jobApplication for the job
			job.getJobApplications().add(application);
			jobDao.addJob(job);
			
			//  setting and updating jobApplication for the Applicant
			applicant.getJobApplications().add(application);
			applicantDao.addApplicant(applicant);
			
			ResponseStructure<JobApplication> responseStructure = new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.CREATED.value());
			responseStructure.setMessage("Job Application added Successfully!!");
			responseStructure.setData(application);
			return new ResponseEntity<ResponseStructure<JobApplication>> (responseStructure, HttpStatus.CREATED);
			
		}else {
			throw new JobNotFoundByIdException("Failed to create job Application!!");
			
		}
		
	}else {
		throw new ApplicantNotFoundByIdException("Failed to create Job Application!!");
	}
}
	
	public ResponseEntity<ResponseStructure<List<JobApplication>>> getJobApplicationsByApplicant(long applicantId)
	{
		Applicant applicant=applicantDao.getApplicant(applicantId);
		if(applicant!=null)
		{ 
	
			List<JobApplication> jobApplications =jobapplicationDao.getJobApplicationsByApplicant(applicantId);
			if(jobApplications!=null)
			{
				if(jobApplications.isEmpty()) 
				{
					throw new JobApplicationNotFoundByIdException("no job Application found for req Id");
		          }
				else
				{
					
//				    List<JobApplicationByApplicantDTO> jobApplicationByApplicantDTOs = 
//				    		jobApplications.stream()
//										    .map(jobApplication -> JobApplicationByApplicantDTO.builder()
//										    		.jobApplicationDateTime(jobApplication.getJobApplicationDateTime())
//										    		.jobApplicationId(jobApplication.getJobApplicationId())
//										    		.job(modelMapper.map(jobApplication.getJob(), JobDto.class))
//										    		.build())
//										    .collect(Collectors.toList());
				    List<JobApplicationByApplicantDTO> jobApplicationByApplicantDTOs = new ArrayList<>();
				    for(JobApplication jobApplication : jobApplications) {
				    	JobApplicationByApplicantDTO dto = new JobApplicationByApplicantDTO();
				    	dto.setJob(modelMapper.map(jobApplication.getJob(), JobDto.class));
				    	dto.setJobApplicationDateTime(jobApplication.getJobApplicationDateTime());
				    	dto.setJobApplicationId(jobApplication.getJobApplicationId());
				    	jobApplicationByApplicantDTOs.add(dto);
				    }
				    
					ResponseStructure<List<JobApplication>> responseStructure=new ResponseStructure<>();
			        responseStructure.setStatusCode(HttpStatus.FOUND.value());
			       responseStructure.setMessage("JobApplication Found By Applicant!");
			       responseStructure.setData(jobApplicationByApplicantDTOs);
			      return new ResponseEntity<ResponseStructure<List<JobApplication>>>(responseStructure,HttpStatus.FOUND);
					
				}
			}
		    else
				 {
					throw new JobApplicationNotFoundByIdException("Failed to find job applications");
				}
		   }
			
			
	  
		
		
	   else
	     {

		throw new ApplicantNotFoundByIdException("Failed to get required JobApplication for particular applicantID");
	     }
}
	
	public ResponseEntity<ResponseStructure<List<JobApplication>>> getJobApplicationsByJob(long jobId)
	{	Job job=jobDao.getJob(jobId);
	if(job!=null)
	{
		List<JobApplication> jobApplications =jobapplicationDao.getJobApplicationsByJob(jobId);
		if(jobApplications.isEmpty())
		{
			throw new JobApplicationNotFoundByIdException("no job Application found for req Id");
		}
		else
		{
			List<JobApplicantionByJobDTO> jobApplicationByJobDTOs = new ArrayList<>();
		    for(JobApplication jobApplication : jobApplications)
		    {
		    	JobApplicantionByJobDTO dto = new JobApplicantionByJobDTO();
		    	dto.setApplicantDto(modelMapper.map(jobApplication.getApplicant(), ApplicantDto.class));
		    	dto.setJobApplicationDateTime(jobApplication.getJobApplicationDateTime());
		    	dto.setJobApplicationId(jobApplication.getJobApplicationId());
		    	jobApplicationByJobDTOs.add(dto);
		    	
		    	
		}
			ResponseStructure<List<JobApplication>> responseStructure=new ResponseStructure<>();
	        responseStructure.setStatusCode(HttpStatus.FOUND.value());
	       responseStructure.setMessage("JobApplication Found By Job!");
	       responseStructure.setData(jobApplicationByJobDTOs);
	      return new ResponseEntity<ResponseStructure<List<JobApplication>>>(responseStructure,HttpStatus.FOUND);
			
		}
	}
	else
	{
	throw new JobNotFoundByIdException("Failed to get required JobApplication for particular jobID");
     }	
	
}
	
	

	
}


