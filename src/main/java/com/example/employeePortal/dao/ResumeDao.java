package com.example.employeePortal.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.employeePortal.entity.Applicant;
import com.example.employeePortal.entity.Resume;
import com.example.employeePortal.repo.ApplicantRepo;
import com.example.employeePortal.repo.ResumeRepo;
@Repository
public class ResumeDao {
	@Autowired
	private ResumeRepo resumeRepo;
	
	public Resume saveResume(Resume resume)
	{
		return resumeRepo.save(resume);
	}
	public Resume getResume(long resumeId)
	{
		Optional<Resume> optional=resumeRepo.findById(resumeId);
		if(optional.isEmpty())
		{
			return null;
		}
		else
		{
			return optional.get();
		}

	}public void deleteResume(Resume resume)
	{
		
	 resumeRepo.delete(resume);
			
		

	}
	
}
