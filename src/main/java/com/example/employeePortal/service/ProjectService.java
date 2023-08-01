package com.example.employeePortal.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.employeePortal.dao.ApplicantDao;
import com.example.employeePortal.dao.ProjectDao;
import com.example.employeePortal.dao.ResumeDao;
import com.example.employeePortal.dto.ProjectDto;
import com.example.employeePortal.entity.Applicant;
import com.example.employeePortal.entity.Project;
import com.example.employeePortal.entity.Resume;
import com.example.employeePortal.exception.ApplicantNotFoundByIdException;
import com.example.employeePortal.exception.ProjectNotFoundByIdException;
import com.example.employeePortal.exception.ResumeNotFoundByIdException;
import com.example.employeePortal.util.ResponseStructure;

@Service
public class ProjectService {
	
	@Autowired
	private ProjectDao projectDao;
	@Autowired
	private ApplicantDao applicantDao;
	@Autowired
	private ResumeDao resumeDao;
	@Autowired
	private ModelMapper modelMapper;
	
	public ResponseEntity<ResponseStructure<Resume>> saveProjects(long applicantId, ProjectDto projectDto){
		Applicant applicant = applicantDao.getApplicant(applicantId);
		if(applicant!=null) 
		{
			Resume resume = applicant.getResume();
			if(resume!=null) 
			{
				List<Project> exProjects = resume.getProjects();
				Project project = this.modelMapper.map(projectDto, Project.class);
				project = projectDao.saveProjects(project);
				exProjects.add(project);
				resumeDao.saveResume(resume);
				ResponseStructure<Resume> responseStructure = new ResponseStructure<>();
				responseStructure.setStatusCode(HttpStatus.CREATED.value());
				responseStructure.setMessage("Project added successfully!!");
				responseStructure.setData(resume);
				return new ResponseEntity<ResponseStructure<Resume>>(responseStructure, HttpStatus.CREATED);
			}else
			{
				throw new ResumeNotFoundByIdException("Failed to add Projects!!");
			}
		}
		
		else
		{
			throw new ApplicantNotFoundByIdException("Failed to add Projects!!");
		}
	}
	
public ResponseEntity<ResponseStructure<Project>> getProjects(long projectId)
{
	Optional<Project> optional=projectDao.getProjects(projectId);
	if(optional.isPresent())
	{
		ResponseStructure<Project> responseStructure=new ResponseStructure<>();
        responseStructure.setStatusCode(HttpStatus.FOUND.value());
       responseStructure.setMessage("Project Found!");
       responseStructure.setData(optional.get());
      return new ResponseEntity<ResponseStructure<Project>>(responseStructure,HttpStatus.FOUND);
       }
	
else
{
	throw new ProjectNotFoundByIdException("Failed to get required Project");
}
}
	
public ResponseEntity<ResponseStructure<Project>> updateProject(long projectId,ProjectDto projectDto)
{Optional<Project> optional =projectDao.getProjects(projectId);
if(optional.isPresent())
{
	
	Project project=this.modelMapper.map(projectDto,Project.class);
	
	project.setProjectId(projectId);
		project=projectDao.saveProjects(project);
		ResponseStructure<Project> responseStructure=new ResponseStructure<>();
        responseStructure.setStatusCode(HttpStatus.FOUND.value());
       responseStructure.setMessage("Project Found!");
       responseStructure.setData(optional.get());
      return new ResponseEntity<ResponseStructure<Project>>(responseStructure,HttpStatus.FOUND);
       }
	
else
{
	throw new ProjectNotFoundByIdException("Failed to Update required Project");
}	
}
public ResponseEntity<ResponseStructure<Project>> deleteProject(long projectId,long applicantId)
{
	Optional<Project> optional=projectDao.getProjects(projectId);
	if(optional.isPresent())
	{
		Applicant applicant=applicantDao.getApplicant(applicantId);
		if(applicant!=null)
		{
			Resume resume=applicant.getResume();
			if(resume!=null) 
			{
				resume.getProjects().remove(optional.get());
				resumeDao.saveResume(resume);
			}
			projectDao.deleteProject(optional.get());
			ResponseStructure<Project> responseStructure=new ResponseStructure<>();
	        responseStructure.setStatusCode(HttpStatus.OK.value());
	       responseStructure.setMessage("Project deleted!");
	       responseStructure.setData(optional.get());
	      return new ResponseEntity<ResponseStructure<Project>>(responseStructure,HttpStatus.OK);
		}
		
		else
		{
			throw new ApplicantNotFoundByIdException("Failed to delete Project");
		}
		
	}
	
	else
	{
		throw new ProjectNotFoundByIdException("Failed to delete required Project");
	}
}
}
	
	



