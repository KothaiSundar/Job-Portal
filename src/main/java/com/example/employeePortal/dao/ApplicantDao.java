package com.example.employeePortal.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.employeePortal.entity.Applicant;
import com.example.employeePortal.entity.Employer;
import com.example.employeePortal.entity.Skill;
import com.example.employeePortal.repo.ApplicantRepo;
@Repository
public class ApplicantDao {
@Autowired
private ApplicantRepo applicantRepo;
public Applicant addApplicant(Applicant applicant)
{
	return applicantRepo.save(applicant);
}
public Applicant getApplicant(long applicantId)
{
	Optional<Applicant> optional=applicantRepo.findById(applicantId);
	if(optional.isEmpty())
	{
		return null;
	}
	else
	{
		return optional.get();
	}

}
public Optional<Skill> getSkillBySkillName(String skill)
{
 return applicantRepo.getSkillBySkillName(skill);

}
public Applicant deleteApplicant(long applicantId)
{
	Optional<Applicant> optional=applicantRepo.findById(applicantId);
	if(optional.isEmpty())
	{
		return null;
	}
	else
	{    applicantRepo.deleteById(applicantId);
		return optional.get();
	}

}

}
