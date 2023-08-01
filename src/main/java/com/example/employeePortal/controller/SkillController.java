package com.example.employeePortal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.employeePortal.entity.Resume;

import com.example.employeePortal.service.SkillService;
import com.example.employeePortal.util.ResponseStructure;

@RestController
@RequestMapping("/skills")
public class SkillController
{
@Autowired
private SkillService skillService;


	@PostMapping
	public ResponseEntity<ResponseStructure<Resume>> saveSkill(@RequestParam long applicantId,@RequestParam String[] skills)

	{
		return skillService.saveSkillToApplicant(applicantId,skills);
	}
}

