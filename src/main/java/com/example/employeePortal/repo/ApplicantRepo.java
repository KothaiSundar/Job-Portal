package com.example.employeePortal.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.employeePortal.entity.Applicant;
import com.example.employeePortal.entity.JobApplication;
import com.example.employeePortal.entity.Skill;


public interface ApplicantRepo extends JpaRepository<Applicant,Long> {
	@Query(value= "select a.jobApplications from Applicant a where a.applicantId=?1")
	public Optional<List<JobApplication>> getJobApplications(long applicantId);
	
	@Query(value= "select s from Skill s where s.skillName=?1")
	public Optional<Skill> getSkillBySkillName(String skill);

}
