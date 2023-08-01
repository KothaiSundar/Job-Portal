package com.example.employeePortal.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.employeePortal.entity.Job;
import com.example.employeePortal.entity.Project;

import com.example.employeePortal.repo.ProjectRepo;
@Repository
public class ProjectDao {
	@Autowired
	private ProjectRepo projectRepo;

	
	public Project saveProjects(Project project)
{
	return projectRepo.save(project);
}
    public Optional<Project> getProjects(long projectId)
    {
    return projectRepo.findById(projectId);
    }
public void deleteProject(Project project)
{ projectRepo.delete(project);
}
}