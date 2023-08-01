package com.example.employeePortal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.employeePortal.dto.ApplicantDto;
import com.example.employeePortal.dto.ApplicantResponse;
import com.example.employeePortal.entity.Applicant;
import com.example.employeePortal.service.ApplicantService;
import com.example.employeePortal.util.ResponseStructure;

@RestController
@RequestMapping("/applicant")
public class ApplicantController {
@Autowired
private ApplicantService applicantService;
@PostMapping
public ResponseEntity<ResponseStructure<ApplicantDto>> saveApplicant(@RequestBody Applicant applicant)
{
	return applicantService.saveApplicant(applicant);
}
@GetMapping
public ResponseEntity<ResponseStructure<ApplicantDto>> getApplicant(@RequestParam long applicantId)
{
	return applicantService.getApplicant(applicantId);
}

@GetMapping("/skill")
public ResponseEntity<ResponseStructure<List<ApplicantResponse>>> getApplicantBySkill(@RequestParam String skill)
{
	return applicantService.getApplicantBySkill(skill);
}
@PutMapping
public ResponseEntity<ResponseStructure<ApplicantDto>> updateApplicant(@RequestParam long applicantId,@RequestBody Applicant applicant)
{
	return applicantService.updateApplicant(applicantId,applicant);
}
@DeleteMapping
public ResponseEntity<ResponseStructure<ApplicantDto>> deleteApplicant(@RequestParam long applicantId)
{
	return applicantService.deleteApplicant(applicantId);
}
}
