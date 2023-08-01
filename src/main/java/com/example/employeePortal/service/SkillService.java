package com.example.employeePortal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.employeePortal.dao.ApplicantDao;
import com.example.employeePortal.dao.ResumeDao;
import com.example.employeePortal.dao.SkillDao;
import com.example.employeePortal.entity.Applicant;
import com.example.employeePortal.entity.Resume;
import com.example.employeePortal.entity.Skill;
import com.example.employeePortal.exception.ApplicantNotFoundByIdException;
import com.example.employeePortal.exception.ResumeNotFoundByIdException;
import com.example.employeePortal.util.ResponseStructure;

@Service
public class SkillService {
	
	@Autowired
	private SkillDao skillDao;
	@Autowired
	private ApplicantDao applicantDao;
	@Autowired
	private ResumeDao resumeDao;
	
	public ResponseEntity<ResponseStructure<Resume>> saveSkillToApplicant(long applicantId ,String[] skills)
	{
		Applicant applicant = applicantDao.getApplicant(applicantId);
		if(applicant!=null) 
		{
			Resume resume = applicant.getResume();
			if(resume!=null) 
			{
			/*
			 * - iterate over the String arrays skills that is received
			 * check if the skill is present with matching name,
			 * if present add the existing skill to the resume
			 * or else create an new skill
			 * *
                  *previously we had one to many from resume to skill
                *we will be changing that to many to many
                *because one person knows java means other person can also know
                *many persons can know many skills*/
				List<Skill> exSkills=resume.getSkills();
				for(String skill : skills)
				{//calling DaoClass to know skill name
					//there is no default method for getting name in repository
					// so write query in repository
					Skill existingskill = skillDao.getSkillByName(skill);//checking in Database
					
					
					if(existingskill!=null) 
						//if already skill present in Database
					{
						if(!exSkills.contains(existingskill)) 
					
						{//not containing existing skill it will perform
							// adding that existing skill to resume
							
							exSkills.add(existingskill);
						}
						
					}
						else 
						{//storing new Skill
							Skill newSkill = new Skill();
							newSkill.setSkillName(skill);
							skillDao.saveSkill(newSkill);
							resume.getSkills().add(newSkill);
						}
					
					
				   }	/*
				 * setting the exSkills list to the resume*/
				resume.setSkills(exSkills);
				
				resume = resumeDao.saveResume(resume);//updating skill
				ResponseStructure<Resume> responseStructure = new ResponseStructure<>();
				responseStructure.setStatusCode(HttpStatus.CREATED.value());
				responseStructure.setMessage("Resume added successfully!!");
				responseStructure.setData(resume);
				return new ResponseEntity<ResponseStructure<Resume>> (responseStructure, HttpStatus.CREATED);
				}
			
				else 
				{
				throw new ResumeNotFoundByIdException("Failed to add skills!!");
			
			     }
		     }
			
			else {
			throw new ApplicantNotFoundByIdException("Failed to add Resume!!");
			
		}
	}
		
}

