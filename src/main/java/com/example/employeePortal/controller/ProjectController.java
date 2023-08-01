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
import org.springframework.web.bind.annotation.RestController;

import com.example.employeePortal.dto.ProjectDto;
import com.example.employeePortal.entity.Project;
import com.example.employeePortal.entity.Resume;
import com.example.employeePortal.service.ProjectService;
import com.example.employeePortal.util.ResponseStructure;

@RestController
@RequestMapping("/projects")
public class ProjectController {
	
	@Autowired
	private ProjectService projectService;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Resume>> saveProject(
			@RequestParam long applicantId, @RequestBody ProjectDto projectDto){
		return projectService.saveProjects(applicantId, projectDto);
	}
	@GetMapping
	public ResponseEntity<ResponseStructure<Project>> getProjects(
			@RequestParam long projectId)
	{
		return projectService.getProjects(projectId);
	}
	@PutMapping
	public ResponseEntity<ResponseStructure<Project>> updateProject(
			@RequestParam long projectId,@RequestBody ProjectDto projectDto)
	{
		return projectService.updateProject(projectId,projectDto);
	}
	@DeleteMapping
	public ResponseEntity<ResponseStructure<Project>> deleteProject(
			@RequestParam long projectId,@RequestParam long applicantId)
	{
		return projectService.deleteProject(projectId,applicantId);
	}
	
}
