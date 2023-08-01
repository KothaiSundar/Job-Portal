package com.example.employeePortal.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.employeePortal.util.ResponseStructure;
@RestControllerAdvice
public class EmployerExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler// making this method as exe handler
	public ResponseEntity<ResponseStructure<String>> EmployerNotFoundById(EmployerNotFoundByIdException ex)
	{
		
	ResponseStructure<String> responseStructure = new ResponseStructure<>();
	responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
	responseStructure.setMessage(ex.getMessage());// message sent in find by id will be sent here (from service)
	responseStructure.setData("Employer not found with req id");
	return new ResponseEntity<ResponseStructure<String>>(responseStructure,HttpStatus.NOT_FOUND);

	}
	@ExceptionHandler// making this method as exe handler
	public ResponseEntity<ResponseStructure<String>> JobNotFoundById(JobNotFoundByIdException ex)
	{
		
	ResponseStructure<String> responseStructure = new ResponseStructure<>();
	responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
	responseStructure.setMessage(ex.getMessage());// message sent in find by id will be sent here (from service)
	responseStructure.setData("Job not found with req id");
	return new ResponseEntity<ResponseStructure<String>>(responseStructure,HttpStatus.NOT_FOUND);

	}
	@ExceptionHandler// making this method as exe handler
	public ResponseEntity<ResponseStructure<String>> ResumeNotFoundById(ResumeNotFoundByIdException ex)
	{
		
	ResponseStructure<String> responseStructure = new ResponseStructure<>();
	responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
	responseStructure.setMessage(ex.getMessage());// message sent in find by id will be sent here (from service)
	responseStructure.setData("Resume not found with req id");
	return new ResponseEntity<ResponseStructure<String>>(responseStructure,HttpStatus.NOT_FOUND);

	}
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> ApplicantNotFoundById(ApplicantNotFoundByIdException ex)
	{
		
	ResponseStructure<String> responseStructure = new ResponseStructure<>();
	responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
	responseStructure.setMessage(ex.getMessage());// message sent in find by id will be sent here (from service)
	responseStructure.setData("Applicant not found with req id");
	return new ResponseEntity<ResponseStructure<String>>(responseStructure,HttpStatus.NOT_FOUND);

	}
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> ProjectNotFoundById(ProjectNotFoundByIdException ex)
	{
		
	ResponseStructure<String> responseStructure = new ResponseStructure<>();
	responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
	responseStructure.setMessage(ex.getMessage());// message sent in find by id will be sent here (from service)
	responseStructure.setData("Project not found with req id");
	return new ResponseEntity<ResponseStructure<String>>(responseStructure,HttpStatus.NOT_FOUND);

	}
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> jobApplicationNotFoundById(JobApplicationNotFoundByIdException ex)
	{
		
	ResponseStructure<String> responseStructure = new ResponseStructure<>();
	responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
	responseStructure.setMessage(ex.getMessage());// message sent in find by id will be sent here (from service)
	responseStructure.setData("Job Application not found with req id");
	return new ResponseEntity<ResponseStructure<String>>(responseStructure,HttpStatus.NOT_FOUND);

	}@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> JobNotFoundBySkill(JobNotFoundBySkillException ex)
	{
		
	ResponseStructure<String> responseStructure = new ResponseStructure<>();
	responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
	responseStructure.setMessage(ex.getMessage());// message sent in find by id will be sent here (from service)
	responseStructure.setData("Job  not found with req skill id");
	return new ResponseEntity<ResponseStructure<String>>(responseStructure,HttpStatus.NOT_FOUND);

	}
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> SkillNotFoundByIdException(SkillNotFoundByIdException ex)
	{
		
	ResponseStructure<String> responseStructure = new ResponseStructure<>();
	responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
	responseStructure.setMessage(ex.getMessage());// message sent in find by id will be sent here (from service)
	responseStructure.setData("Skill not found with req id");
	return new ResponseEntity<ResponseStructure<String>>(responseStructure,HttpStatus.NOT_FOUND);

	}
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> SkillNotFoundByNameException(SkillNotFoundByNameException ex)
	{
		
	ResponseStructure<String> responseStructure = new ResponseStructure<>();
	responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
	responseStructure.setMessage(ex.getMessage());// message sent in find by id will be sent here (from service)
	responseStructure.setData("Skill not found with req name");
	return new ResponseEntity<ResponseStructure<String>>(responseStructure,HttpStatus.NOT_FOUND);

	}
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> AlreadyAppliedToJobException(AlreadyAppliedToJobException ex)
	{
		
	ResponseStructure<String> responseStructure = new ResponseStructure<>();
	responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
	responseStructure.setMessage(ex.getMessage());// message sent in find by id will be sent here (from service)
	responseStructure.setData("Job already applied");
	return new ResponseEntity<ResponseStructure<String>>(responseStructure,HttpStatus.NOT_FOUND);

	}
	
}
