package com.example.employeePortal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.employeePortal.dto.ResumeDto;
import com.example.employeePortal.entity.Resume;
import com.example.employeePortal.service.ResumeService;
import com.example.employeePortal.util.ResponseStructure;

@RestController
@RequestMapping("/resume")
public class ResumeController {
@Autowired
private ResumeService resumeService;
@PostMapping
public ResponseEntity<ResponseStructure<Resume>> saveResume(@RequestParam long applicantId,@RequestBody ResumeDto resumeDto)

{
	return resumeService.saveResume(applicantId,resumeDto);
}
@GetMapping
public ResponseEntity<ResponseStructure<Resume>> getResume(@RequestParam long resumeId)

{
	return resumeService.getResume(resumeId);
}


@PutMapping
public ResponseEntity<ResponseStructure<Resume>> getResume(@RequestParam long resumeId,@RequestBody ResumeDto resumeDto)

{
	return resumeService.saveResume(resumeId,resumeDto);
}
}
