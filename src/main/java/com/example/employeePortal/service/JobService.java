package com.example.employeePortal.service;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.employeePortal.dao.EmployerDao;
import com.example.employeePortal.dao.JobApplicationDao;
import com.example.employeePortal.dao.JobDao;
import com.example.employeePortal.dao.SkillDao;
import com.example.employeePortal.dto.JobDto;
import com.example.employeePortal.dto.JobResponse;
import com.example.employeePortal.entity.Employer;
import com.example.employeePortal.entity.Job;
import com.example.employeePortal.entity.JobApplication;
import com.example.employeePortal.entity.Skill;
import com.example.employeePortal.exception.EmployerNotFoundByIdException;
import com.example.employeePortal.exception.JobNotFoundByIdException;
import com.example.employeePortal.exception.JobNotFoundBySkillException;
import com.example.employeePortal.exception.SkillNotFoundByIdException;
import com.example.employeePortal.util.ResponseStructure;

import edu.training_student_management_system.entity.Admin;
@Service
public class JobService {

	@Autowired
	private JobDao jobDao;
	@Autowired
	private EmployerDao employerDao;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private SkillDao skillDao;
	@Autowired
	private JobApplicationDao applicationDao;
	
	public ResponseEntity<ResponseStructure<JobResponse>> addJob(JobDto jobDto,long employerId,String[] skills) 
	
	{
        Employer employer=employerDao.getEmployer(employerId);
if(employer!=null)
{Job job = this.modelMapper.map(jobDto,Job.class);// provide object that has to be mapped to
//that is map to job.class
//attributes of jobDto those only converted to job class

		

		// instead of getting from DtoClass this we can add dependency model mapper and write a method in applicationclass
//	 Job job = new Job();
//	                                             
//	 job.setJobTitle(jobDto.getJobTitle());
//	 job.setJobDescription(jobDto.getJobDescription());
//	 job.setJobCompany(jobDto.getJobCompany());
//	 job.setJobSalary(jobDto.getJobSalary());
	 
		
		job.setJobCreateDateTime(LocalDateTime.now());// it is not in DtoClass so writing this
	 
	 job.setEmployer(employer);//  bidirectional so set employer to job
	 job=jobDao.addJob(job);

		/*
		 * checking if the skill is already present in the database, if present add same
		 * skill to the job, or else create the new skill
		 */
	List<Skill> jobSkills=new ArrayList<>();
	for(String skill: skills)
	{
	  Skill exSkill=skillDao.getSkillByName(skill);
	  if(exSkill!=null)
	  {
		if(!jobSkills.contains(exSkill))
		{
			jobSkills.add(exSkill);
		}
	  }	
		else
		{
			Skill newSkill=new Skill();
			newSkill.setSkillName(skill);
			skillDao.saveSkill(newSkill);
			jobSkills.add(newSkill);
		}
 }
	
	//setting exSkill to job
	  job.setSkills(jobSkills);
	  employer.getJobs().add(job); //directly adding job to jobList of Employer
		 
		 //or can do like this
		 //List<Job> jobs=employer.getJobs();
			// jobs.add(job);
			 
		
      employerDao.addEmployer(employer);//to update the new job to jobList of empDao..here add and update do samework
		JobResponse response=this.modelMapper.map(job,JobResponse.class);
		List<String> responseSkills=new ArrayList<>();
		for(Skill skill:job.getSkills())
		{
			responseSkills.add(skill.getSkillName());
		}
		response.setSkills(responseSkills);
		
		
	
	 
	 jobDto.setJobId(job.getJobId());
	 
	 ResponseStructure<JobResponse> responseStructure=new ResponseStructure<>();
	 responseStructure.setStatusCode(HttpStatus.CREATED.value());
	 responseStructure.setMessage("Job added successfully");
	 responseStructure.setData(response);
	 return new ResponseEntity<ResponseStructure<JobResponse>>(responseStructure, HttpStatus.CREATED);
			
}
else
{
	throw new EmployerNotFoundByIdException("Failed to find Employer Id");
	
}

	}
	
	
	public ResponseEntity<ResponseStructure<JobResponse>> getJob(long jobId) 
	{Job job=jobDao.getJob(jobId);
	if(job!=null)
	{
		JobResponse response = this.modelMapper.map(job, JobResponse.class);
	List<String> responseSkills = new ArrayList<>();
	// to get only skills name if we get a job we are using jobResponse
	//if we Did not use then both skillId and skillName will be printed if we get a job
	for(Skill skill : job.getSkills()) {
		responseSkills.add(skill.getSkillName());
	}
	response.setSkills(responseSkills);
	 
	 ResponseStructure<JobResponse> responseStructure=new ResponseStructure<>();
	 responseStructure.setStatusCode(HttpStatus.FOUND.value());
	 responseStructure.setMessage("Job is found");
	 responseStructure.setData(response);
	 return new ResponseEntity<ResponseStructure<JobResponse>>(responseStructure, HttpStatus.FOUND);
			
	
		
	}else
	{
		throw new JobNotFoundByIdException("Failed to find Job Id");
		
	}
		
	}
	public ResponseEntity<ResponseStructure<List<JobResponse>>> getJobsBySkill(long skillId)
	{
		Skill skill=skillDao.getSkillById(skillId);
		if(skill!=null)
		{
			List<Job> jobs=skill.getJobs();
			List<JobResponse> responses=new ArrayList<>();
			if(!jobs.isEmpty())
			{for(Job job:jobs)
			{
				JobResponse response=this.modelMapper.map(job,JobResponse.class);
				List<String> skills=new ArrayList<>();
				for(Skill s:job.getSkills())
				{
					skills.add(s.getSkillName());
				}
				response.setSkills(skills);
				responses.add(response);
			}

			 ResponseStructure<List<JobResponse>> responseStructure=new ResponseStructure<>();
			 responseStructure.setStatusCode(HttpStatus.FOUND.value());
			 responseStructure.setMessage("Job FOUND");
			 responseStructure.setData(responses);
			
			 
			 return new ResponseEntity<ResponseStructure<List<JobResponse>>>(responseStructure, HttpStatus.FOUND);
					
			
				
			} 
			else
			{
				throw new JobNotFoundBySkillException("Failed to find Job ");
				
			}
			
		}
			else 
			{
				throw new SkillNotFoundByIdException("Failed to find Jobs!!");
			}
				 
			
		
		
	}

	
	public ResponseEntity<ResponseStructure<Job>>updateJob(long jobId,JobDto jobDto) 
	{Job job1=jobDao.getJob(jobId);
	// error not getting jobId in postman
	
	if(job1!=null)
	{Job job2 = this.modelMapper.map(jobDto,Job.class);
	
	job2.setJobId(jobId);
	job2.setJobCreateDateTime(job1.getJobCreateDateTime());
	job2.setEmployer(job1.getEmployer());
	job2.setJobApplications(job1.getJobApplications());
job2.setSkills(job1.getSkills());
	job2=jobDao.addJob(job2);
	JobResponse response = this.modelMapper.map(job2, JobResponse.class);
	List<String> skills = new ArrayList<>();
	for(Skill skill : job2.getSkills()) {
		skills.add(skill.getSkillName());
	}
	response.setSkills(skills);

	 ResponseStructure<Job> responseStructure=new ResponseStructure<>();
	 responseStructure.setStatusCode(HttpStatus.OK.value());
	 responseStructure.setMessage("Job is updated");
	 responseStructure.setData(response);
	
	 
	 return new ResponseEntity<ResponseStructure<Job>>(responseStructure, HttpStatus.OK);
			
	
		
	} 
	else
	{
		throw new JobNotFoundByIdException("Failed to update Job ");
		
	}
		
	}
	
	public ResponseEntity<ResponseStructure<Job>>deleteJob(long jobId) 

        {Job job=jobDao.getJob(jobId);
        if(job!=null)
        {// can't delete job in job application...so set null for job in jobApplicaiton
        	for(JobApplication application:job.getJobApplications())
        	{
        		application.setJob(null);
        		applicationDao.createJobApplication(application);
        	}
        	JobResponse response=this.modelMapper.map(job, JobResponse.class);
        	List<String> responseSkills=new ArrayList<>();
        	for(Skill skill:job.getSkills())
        	{
        		responseSkills.add(skill.getSkillName());
        		
        	}response.setSkills(responseSkills);
        	jobDao.deleteJob(jobId);
        	
        ResponseStructure<Job> responseStructure=new ResponseStructure<>();
   	 responseStructure.setStatusCode(HttpStatus.OK.value());
   	 responseStructure.setMessage("Job is deleted");
   	 responseStructure.setData(response);
   	
   	 
   	 return new ResponseEntity<ResponseStructure<Job>>(responseStructure, HttpStatus.OK);
   			
   	
   		
   	}
   	else
   	{
   		throw new JobNotFoundByIdException("Failed to delete Job ");
   		
   	}
		}
}