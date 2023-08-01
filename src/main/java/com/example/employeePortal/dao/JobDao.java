package com.example.employeePortal.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import com.example.employeePortal.entity.Job;

import com.example.employeePortal.repo.JobRepo;
@Repository
public class JobDao {
	@Autowired
	private JobRepo jobRepo;


	public Job addJob(Job job1) 
	{
		
		return jobRepo.save(job1);
	}
	
	
	
	
	public Job getJob(long jobId) 
	{
	Optional<Job> optional= jobRepo.findById(jobId);

	if(optional.isEmpty())
	{
		return null;
	}
	else
	{
		return optional.get();
	}

	}
	
	
	
	
	public Job updateJob(long jobId,Job job) 
	{
	Optional<Job> optional= jobRepo.findById(jobId);

	if(optional.isEmpty())
	{
		return null;
	}
	else
	{job.setJobId(jobId);
		return jobRepo.save(job);
	}

	}
	
	public Job deleteJob(long jobId)
	{
		Optional<Job> optional=jobRepo.findById(jobId);
		if(optional.isEmpty())
		{
			return null;
		}
		else
		{jobRepo.deleteById(jobId);
			return optional.get();
		}
	}
}
