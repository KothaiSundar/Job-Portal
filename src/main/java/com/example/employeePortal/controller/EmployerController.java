package com.example.employeePortal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.employeePortal.dto.EmployerDto;
import com.example.employeePortal.entity.Employer;
import com.example.employeePortal.service.EmployerService;
import com.example.employeePortal.util.ResponseStructure;



@RestController
@RequestMapping("/employer")
public class EmployerController 

{   @Autowired 
	private EmployerService employerService;
	@PostMapping
	public ResponseEntity<ResponseStructure<Employer>> addEmployer(@RequestBody Employer employer)
	{
		return employerService.addEmployer(employer);
	}
	@GetMapping
	public ResponseEntity<ResponseStructure<Employer>> getEmployer(@RequestParam long employerId)
	{
		return employerService.getEmployer(employerId);
	}
	
	
	@PutMapping
	public ResponseEntity<ResponseStructure<EmployerDto>> updateEmployer(@RequestParam long employerId,@RequestBody Employer employer)
	{
		return employerService.updateEmployer(employerId,employer);
	}
	
	@DeleteMapping
	public ResponseEntity<ResponseStructure<EmployerDto>> deleteEmployer(@RequestParam long employerId)
	{
		return employerService.deleteEmployer(employerId);
	}
	
	
}

