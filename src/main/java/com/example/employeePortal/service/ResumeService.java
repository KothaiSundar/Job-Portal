package com.example.employeePortal.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.employeePortal.dao.ApplicantDao;
import com.example.employeePortal.dao.ResumeDao;
import com.example.employeePortal.dto.ApplicantDto;
import com.example.employeePortal.dto.ResumeDto;
import com.example.employeePortal.entity.Applicant;
import com.example.employeePortal.entity.Resume;
import com.example.employeePortal.exception.ApplicantNotFoundByIdException;
import com.example.employeePortal.exception.ResumeNotFoundByIdException;
import com.example.employeePortal.util.ResponseStructure;



@Service
public class ResumeService {
@Autowired
private ResumeDao resumeDao;
@Autowired
private ModelMapper modelmapper;

@Autowired
private ApplicantDao applicantDao;
public ResponseEntity<ResponseStructure<Resume>> saveResume(long applicantId,ResumeDto resumeDto)

{//accepting object of resumeDto because no secret data in resumeDto
	
	Applicant applicant=applicantDao.getApplicant(applicantId);


	if(applicant!=null)
	{
		Resume existingResume=applicant.getResume();//getting previous resume
		// if no resume for particular applicant id then null in existing resume
	    Resume resume=this.modelmapper.map(resumeDto,Resume.class);
	
	             //for one ApplicantId, there will be one resume
	                if(existingResume!=null)//act as update and as save
			         {    // if present just update the resume and if not present it saves   
	        	   resume.setResumeId(existingResume.getResumeId());
	                 resume.setProjects(existingResume.getProjects());;
	                 resume.setSkills(existingResume.getSkills());
			         }
	
	
            
	         resume.setApplicant(applicant);// applicant not present in resumeDto so setting to applicant
	         
	         resume=resumeDao.saveResume(resume);
	         applicant.setResume(resume);
	         applicantDao.addApplicant(applicant);// updating resume to resume list of applicant
	
	
	       ResponseStructure<Resume> responseStructure=new ResponseStructure<>();
	        responseStructure.setStatusCode(HttpStatus.CREATED.value());
	        responseStructure.setMessage("Resume added successfully");
	           responseStructure.setData(resume);
	
	
	         return new ResponseEntity<ResponseStructure<Resume>> (responseStructure, HttpStatus.CREATED);
	
	     }	
	    else
	    {
		throw new ApplicantNotFoundByIdException("Failed to add Resume");
	
	     }
	
}
public ResponseEntity<ResponseStructure<Resume>> getResume(long resumeId)
{
	
	Resume resume=resumeDao.getResume(resumeId);

     if(resume!=null)
         {
    	 ResponseStructure<Resume> responseStructure=new ResponseStructure<>();
         responseStructure.setStatusCode(HttpStatus.FOUND.value());
        responseStructure.setMessage("Resume Found!");
        responseStructure.setData(resume);
       return new ResponseEntity<ResponseStructure<Resume>>(responseStructure,HttpStatus.FOUND);
          }
	
else
{
	throw new ResumeNotFoundByIdException("Failed to get required resume");
}
}


	

	
}
