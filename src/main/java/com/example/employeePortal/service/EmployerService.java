package com.example.employeePortal.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.employeePortal.dao.EmployerDao;
import com.example.employeePortal.dao.JobDao;
import com.example.employeePortal.dto.EmployerDto;
import com.example.employeePortal.entity.Employer;
import com.example.employeePortal.entity.Job;
import com.example.employeePortal.exception.EmployerNotFoundByIdException;
import com.example.employeePortal.util.ResponseStructure;




@Service
public class EmployerService {
@Autowired
private EmployerDao employerDao;
@Autowired
private EmployerDto employerDto;
@Autowired
private ModelMapper modelMapper;
@Autowired
private JobDao jobDao;

public ResponseEntity<ResponseStructure<Employer>> addEmployer(Employer employer) {
	
	 Employer employer2=employerDao.addEmployer(employer);
	 employerDto.setEmployerId(employer2.getEmployerId());
	 employerDto.setEmployerName(employer2.getEmployerName());
	 employerDto.setEmployerEmail(employer2.getEmployerEmail());
	 employerDto.setJobs(employer2.getJobs());
	 
	 
	 ResponseStructure<Employer> responseStructure=new ResponseStructure<>();
	 responseStructure.setStatusCode(HttpStatus.CREATED.value());
	 responseStructure.setMessage("Employer added successfully");
	 responseStructure.setData(employerDto);
	 return new ResponseEntity<ResponseStructure<Employer>>(responseStructure, HttpStatus.CREATED);
			
}



public ResponseEntity<ResponseStructure<Employer>> getEmployer(long employerId)
{   Employer employer=employerDao.getEmployer(employerId);
EmployerDto employerDto=new EmployerDto();

if(employer!=null)
{
	employerDto.setEmployerId(employer.getEmployerId());
	employerDto.setEmployerName(employer.getEmployerName());
	employerDto.setEmployerEmail(employer.getEmployerEmail());
	employerDto.setJobs(employer.getJobs());
	
	ResponseStructure<Employer> responseStructure=new ResponseStructure<>();
	responseStructure.setStatusCode(HttpStatus.FOUND.value());
	responseStructure.setMessage("Employer Id  found");
	responseStructure.setData(employerDto);
return new ResponseEntity<ResponseStructure<Employer>>(responseStructure,HttpStatus.FOUND);
}
else
{
	throw new EmployerNotFoundByIdException("Failed to find Employer Id");
}
}


public ResponseEntity<ResponseStructure<EmployerDto>> updateEmployer(long employerId, Employer employer)
{ Employer exEmployer=employerDao.getEmployer(employerId);
if(exEmployer!=null)
{
	employer.setEmployerId(exEmployer.getEmployerId());
	employer.setJobs(exEmployer.getJobs());
	employer=employerDao.addEmployer(employer);
	
	EmployerDto employerDto = this.modelMapper.map(employer, EmployerDto.class);
	ResponseStructure<EmployerDto> responseStructure=new ResponseStructure<>();
	responseStructure.setStatusCode(HttpStatus.OK.value());
	responseStructure.setMessage("Employer got updated");
	responseStructure.setData(employerDto);

	return new ResponseEntity<ResponseStructure<EmployerDto>>(responseStructure,HttpStatus.OK);
}

else
{
	throw new EmployerNotFoundByIdException("Failed to update Employer ");
}
}
public ResponseEntity<ResponseStructure<EmployerDto>> deleteEmployer(long employerId)
{
	Employer employer=employerDao.getEmployer(employerId);
	if(employer!=null) 
	{
		/* while deleting the employer, the jobs that he created are not
		   deleted , so set the employer as null in job.*/
		
		
		for(Job job:employer.getJobs())
		{
			job.setEmployer(null);
			jobDao.addJob(job);
		}
		employerDao.deleteEmployer(employerId);
		EmployerDto employerDto=this.modelMapper.map(employer, EmployerDto.class);
		
		
		ResponseStructure<EmployerDto> responseStructure=new ResponseStructure<>();
		responseStructure.setStatusCode(HttpStatus.OK.value());
		responseStructure.setMessage("Employer got deleted");
		responseStructure.setData(employerDto);

		return new ResponseEntity<ResponseStructure<EmployerDto>>(responseStructure,HttpStatus.OK);
	}
	else
	{
		throw new EmployerNotFoundByIdException("Failed to delete Employer ");
	}
	}
}

