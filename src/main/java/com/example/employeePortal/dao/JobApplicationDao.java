package com.example.employeePortal.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;

import com.example.employeePortal.entity.Job;
import com.example.employeePortal.entity.JobApplication;

import com.example.employeePortal.repo.JobApplicationRepo;
import com.example.employeePortal.repo.JobRepo;
import com.example.employeePortal.repo.ApplicantRepo;
import edu.training_student_management_system.entity.Student;


@Repository
public class JobApplicationDao {
	@Autowired
	private JobApplicationRepo jobApplicationRepo;
	@Autowired
	private ApplicantRepo applicantRepo;
	@Autowired
	private JobRepo jobRepo;
	
	
public JobApplication createJobApplication(JobApplication jobApplication) {
		
		return jobApplicationRepo.save(jobApplication);
	}

public List<JobApplication> getJobApplicationsByApplicant(long applicantId)
{	
	Optional<List<JobApplication>> optional= applicantRepo.getJobApplications(applicantId);
if(optional.isEmpty())
{
	return null;
}
else
{
	return optional.get();
}


}public List<JobApplication> getJobApplicationsByJob(long jobId)
{	
	Optional<List<JobApplication>> optional= jobRepo.getJobApplications(jobId);
if(optional.isEmpty())
{
	return null;
}
else
{
	return optional.get();
}


}
}