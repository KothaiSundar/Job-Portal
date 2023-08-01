package com.example.employeePortal.service;

import java.util.Optional;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.employeePortal.dao.ApplicantDao;
import com.example.employeePortal.dao.JobApplicationDao;
import com.example.employeePortal.dao.ProjectDao;
import com.example.employeePortal.dao.ResumeDao;
import com.example.employeePortal.dao.SkillDao;
import com.example.employeePortal.dto.ApplicantDto;
import com.example.employeePortal.entity.Applicant;
import com.example.employeePortal.entity.Job;
import com.example.employeePortal.entity.JobApplication;
import com.example.employeePortal.entity.Project;
import com.example.employeePortal.entity.Resume;
import com.example.employeePortal.entity.Skill;
import com.example.employeePortal.exception.ApplicantNotFoundByIdException;
import com.example.employeePortal.exception.SkillNotFoundByNameException;
import com.example.employeePortal.util.ResponseStructure;
import com.example.employeePortal.dto.ApplicantResponse;


@Service
public class ApplicantService
{
	@Autowired
	private ApplicantDao applicantDao;
	@Autowired
	private JobApplicationDao applicantionDao;
	@Autowired
	private SkillDao skillDao;
	@Autowired
	private ResumeDao resumeDao;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private ProjectDao projectDao;
	
	public ResponseEntity<ResponseStructure<ApplicantDto>> saveApplicant(Applicant applicant)
	
	{//accepting object of applicant because password is secret in DtoClass
		//not accepting applicantDto object
		
		applicant=applicantDao.addApplicant(applicant);
	ApplicantDto applicantDto=this.modelMapper.map(applicant, ApplicantDto.class);
	ResponseStructure<ApplicantDto> responseStructure=new ResponseStructure<>();
	 responseStructure.setStatusCode(HttpStatus.CREATED.value());
	 responseStructure.setMessage("Applicant added successfully");
	 responseStructure.setData(applicantDto);
	
	
	return new ResponseEntity<ResponseStructure<ApplicantDto>> (responseStructure, HttpStatus.CREATED);
	
	}
	
	
	
	public ResponseEntity<ResponseStructure<ApplicantDto>> getApplicant(long applicantId)
	{
		Applicant applicant=applicantDao.getApplicant(applicantId);
		if(applicant!=null)
		{
		ApplicantDto applicantDto=this.modelMapper.map(applicant, ApplicantDto.class);
		
		ResponseStructure<ApplicantDto> responseStructure=new ResponseStructure<>();
		responseStructure.setStatusCode(HttpStatus.FOUND.value());
		responseStructure.setMessage("Applicant Found!");
		
		responseStructure.setData(applicantDto);
		return new ResponseEntity<ResponseStructure<ApplicantDto>>(responseStructure,HttpStatus.FOUND);
	     }
	   else 
	    {
		throw new ApplicantNotFoundByIdException("Failed to find Applicant");
     
	   }
		
	}
	public ResponseEntity<ResponseStructure<List<ApplicantResponse>>> getApplicantBySkill(String skill) {
		Optional<Skill> optional = applicantDao.getSkillBySkillName(skill);
		if(optional.isPresent()) {
			Skill exSkill = optional.get();
			List<ApplicantResponse> responses = new ArrayList<>();
			for(Resume resume : exSkill.getResumes()) {
				ApplicantResponse response = this.modelMapper.map(resume.getApplicant(), ApplicantResponse.class);
				responses.add(response);
			}
			ResponseStructure<List<ApplicantResponse>> responseStructure = new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.FOUND.value());
			responseStructure.setMessage("Applicants Found by Skill.");
			responseStructure.setData(responses);
			return new ResponseEntity<ResponseStructure<List<ApplicantResponse>>>(responseStructure, HttpStatus.FOUND);
		}
		
		
		else
		{		
			throw new SkillNotFoundByNameException("Failed to find the Applicats!!");
     	}

		
	}
	
   
	public ResponseEntity<ResponseStructure<ApplicantDto>> updateApplicant(long applicantId,Applicant applicant)
	{
		
		Applicant exApplicant=applicantDao.getApplicant(applicantId);
	
	if(exApplicant!=null)
      {
	
	
		applicant.setApplicantId(exApplicant.getApplicantId());
        applicant.setJobApplications(exApplicant.getJobApplications());
        applicant.setResume(exApplicant.getResume());
        applicantDao.addApplicant(applicant);
        ApplicantDto applicantDto = this.modelMapper.map(applicant, ApplicantDto.class);
		ResponseStructure<ApplicantDto> responseStructure = new ResponseStructure<>();
		responseStructure.setStatusCode(HttpStatus.OK.value());
		responseStructure.setMessage("Applicant updated successfully.");
		responseStructure.setData(applicantDto);
		return new ResponseEntity<ResponseStructure<ApplicantDto>>(responseStructure, HttpStatus.OK);
	}
	else
	{
	throw new ApplicantNotFoundByIdException("Failed to update Applicant!!");

     }
	
		
	}
	public ResponseEntity<ResponseStructure<ApplicantDto>> deleteApplicant(long applicantId)
	{
		Applicant applicant=applicantDao.getApplicant(applicantId);
		if(applicant!=null)
		{/*Before deleting the applicant the applicant is set to null in all the
			  jobApplications, later the applicant is deleted */
			for(JobApplication application: applicant.getJobApplications())
			{
				application.setApplicant(null);
				applicantionDao.createJobApplication(application);// updating null applicant in jobapplication
				
			}
			
		applicantDao.deleteApplicant(applicantId);
		
		//after deleting applicant, resume linked to the applicant should be deleted
		
		Resume resume=applicant.getResume();
		if(resume!=null)
		{// before deleting resume setting skills null to that particular resume
			resume.setSkills(null);
			resumeDao.saveResume(resume);// updating null skills to the resume
			
			//before deleting resume, deleting project of the particular resume
			for(Project project:applicant.getResume().getProjects())
			{
				projectDao.deleteProject(project);
			}
			resumeDao.deleteResume(resume);
		}
		ApplicantDto applicantDto=this.modelMapper.map(applicant,ApplicantDto.class);
			ResponseStructure<ApplicantDto> responseStructure = new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Applicant deleted successfully.");
			responseStructure.setData(applicantDto);
			return new ResponseEntity<ResponseStructure<ApplicantDto>>(responseStructure, HttpStatus.OK);
		}
		else
		{
		throw new ApplicantNotFoundByIdException("Failed to delete Applicant!!");

	     }
			
		}
	}
	
	 